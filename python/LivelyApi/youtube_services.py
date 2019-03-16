'''
Lively YouTube Comments collector
2019, wgl

(youtube_services.py)
'''

import httplib2
import os
import sys
import re
import json
import time, datetime

from googleapiclient.discovery import build_from_document
from oauth2client.client import flow_from_clientsecrets
from oauth2client.file import Storage
from oauth2client.tools import argparser, run_flow

# from langdetect import detect_langs
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize, sent_tokenize

CLIENT_SECRETS_FILE = "resources/client_secrets.json"

YOUTUBE_READ_WRITE_SSL_SCOPE = "https://www.googleapis.com/auth/youtube.force-ssl"
YOUTUBE_API_SERVICE_NAME = "youtube"
YOUTUBE_API_VERSION = "v3"

# This variable defines a message to display if the CLIENT_SECRETS_FILE is
# missing.
MISSING_CLIENT_SECRETS_MESSAGE = """
WARNING: Please configure OAuth 2.0

To make this sample run you will need to populate the client_secrets.json file
found at:
   %s
with information from the APIs Console
https://console.developers.google.com

For more information about the client_secrets.json file format, please visit:
https://developers.google.com/api-client-library/python/guide/aaa_client_secrets
""" % os.path.abspath(os.path.join(os.path.dirname(__file__), CLIENT_SECRETS_FILE))


# ========================= 유튜브 댓글 가져오기 =========================
def get_authenticated_service(channelid, videoid):
  # nltk
  nltk.download('punkt', quiet=True)
  nltk.download('stopwords', quiet=True)

  # e.g) Netflix Youtube ID
  argparser.add_argument("--channelid", default=channelid,
                          help="Required; ID for channel for which the comment will be inserted.")

  # e.g) Netflix Kingdom trailer
  argparser.add_argument("--videoid", default=videoid,
                          help="Required; ID for video for which the comment will be inserted.")

  args = argparser.parse_args()

  if not args.channelid:
    """ exit("Please specify channelid using the --channelid= parameter.") """
    exit("YouTube 채널 ID를 입력해주세요 e.g) https://www.youtube.com/[이 부분]/")
  if not args.videoid:
    """ exit("Please specify videoid using the --videoid= parameter.") """
    exit("YouTube 비디오 ID를 입력해주세요 e.g) https://www.youtube.com/USER-ID/[이 부분]")


  flow = flow_from_clientsecrets(CLIENT_SECRETS_FILE, scope=YOUTUBE_READ_WRITE_SSL_SCOPE,
    message=MISSING_CLIENT_SECRETS_MESSAGE)

  storage = Storage("resources/{}-oauth2.json".format(sys.argv[0]))
  credentials = storage.get()

  if credentials is None or credentials.invalid:
    credentials = run_flow(flow, storage, args)

  # Trusted testers can download this discovery document from the developers page
  # and it should be in the same directory with the code.
  with open("resources/youtube-v3-discoverydocument.json", "r") as f:
    doc = f.read()
    return build_from_document(doc, http=credentials.authorize(httplib2.Http()))


# ========================= 댓글 가져오기 =========================
def get_comments(youtube, video_id, channel_id):
  results = youtube.commentThreads().list(
    part="snippet",
    videoId=video_id,
    channelId=channel_id,
    maxResults=100,   # comment 최대 갯수 (1-100)
    textFormat="plainText"
  ).execute()

  return results["items"]


# ========================= word count =========================
def word_count(text):
  tokenized = word_tokenize(text, language='english')
  words = {}
  for token in tokenized:
    filter_path_str = os.path.abspath(os.path.join(os.path.dirname(__file__), './resources/english_filter'))
    if not token in stopwords.words(filter_path_str) and re.match('^([a-z])\w+$', token):
      if not words.__contains__(token):
        words[token] = 1
      else:
        words[token] += 1

  # list_of_words = []
  # list_of_words += words
  # return list_of_words

  return words

    
# ========================= 댓글 입력 (text 옵션 필요) =========================
def insert_comment(youtube, channel_id, video_id, text):
  insert_result = youtube.commentThreads().insert(
    part="snippet",
    body=dict(
      snippet=dict(
        channelId=channel_id,
        videoId=video_id,
        topLevelComment=dict(
          snippet=dict(
            textOriginal=text
          )
        )
      )
    )
  ).execute()

  comment = insert_result["snippet"]["topLevelComment"]
  author = comment["snippet"]["authorDisplayName"]
  text = comment["snippet"]["textDisplay"]
  # print("Inserted comment for {}: {}".format(author, text))


# ========================= 댓글 수정 =========================
# commentThreads.update 참조
def update_comment(youtube, comment):
  comment["snippet"]["topLevelComment"]["snippet"]["textOriginal"] = 'updated'
  update_result = youtube.commentThreads().update(
    part="snippet",
    body=comment
  ).execute()

  comment = update_result["snippet"]["topLevelComment"]
  author = comment["snippet"]["authorDisplayName"]
  text = comment["snippet"]["textDisplay"]
  
  # print("Updated comment for {}: {}".format(author, text))


# ========================= json 저장 =========================
def json_save(**kwargs):
  result = kwargs.get("from_list")
  channelId = kwargs.get("video_id")
  videoId = kwargs.get("video_id")

  cnt = 1
  for item in result:
    commentThreads = item
    commentThreads_Snippet = commentThreads["snippet"]
    comment = commentThreads_Snippet["topLevelComment"]
    snippet = comment["snippet"]

    commentId = commentThreads["id"]
    # channelId = commentThreads_Snippet.get("channelId")
    # videoId = commentThreads_Snippet.get("videoId")

    publishedAt = snippet["publishedAt"]
    updatedAt = snippet["updatedAt"]
    authorName = snippet["authorDisplayName"]
    authorProfileUrl = snippet["authorProfileImageUrl"]
    authorChannelId = snippet["authorChannelId"]["value"]
    text = snippet["textDisplay"]
    textOriginal = snippet["textOriginal"]
    textLength = len(text)
    canRate = snippet["canRate"]
    viewerRating = snippet["viewerRating"]
    likeCount = snippet["likeCount"]
    canReply = commentThreads_Snippet["canReply"]
    totalReplyCount = commentThreads_Snippet["totalReplyCount"]
    isPublic = commentThreads_Snippet["isPublic"]

    # 중간 확인
    print("{}. {}: {}".format(cnt, authorName, text))
    cnt += 1

    
    # String T,Z제거 -> datetime -> float(timestamp) -> int(long)
    publishedAt = publishedAt.replace("T", "")
    publishedAt = publishedAt.replace("Z", "")
    publishedAt = int(datetime.datetime.strptime(
        publishedAt, "%Y-%m-%d%H:%M:%S.%f").timestamp())
    updatedAt = updatedAt.replace("T", "")
    updatedAt = updatedAt.replace("Z", "")
    updatedAt = int(datetime.datetime.strptime(
        updatedAt, "%Y-%m-%d%H:%M:%S.%f").timestamp())


    wordcounts = word_count(text)

    if len(wordcounts) > 0:
      # json 변환 후 저장
      dirname = 'data/json/' + authorChannelId
      os.makedirs(dirname, exist_ok=True)
      filename = dirname + '/' + commentId + ".json"
      with open(filename, "w", encoding="utf-8") as f:
        json.dump(
            [
                {
                    "commentId": commentId,
                    "channelId": channelId,
                    "videoId": videoId,
                },
                {
                    "publishedAt": publishedAt,
                    "updatedAt": updatedAt,
                    "authorDisplayName": authorName,
                    "authorProfileImageUrl": authorProfileUrl,
                    "authorChannelId": authorChannelId,
                    "textDisplay": text,
                    "textOriginal": textOriginal,
                    "textLength": textLength,
                    "canRate": str(canRate).lower(),
                    "viewerRating": viewerRating,
                    "likeCount": likeCount,
                    "canReply": str(canReply).lower(),
                    "totalReplyCount": totalReplyCount,
                    "isPublic": str(isPublic).lower()
                },
                [wordcounts]
            ]
        , f)


      


 

def txt_save(text):
  print()
