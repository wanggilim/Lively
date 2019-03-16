'''
Lively YouTube Comments collector
2019, wgl

# 사용 예시
# python main.py --channelid='UCWOA1ZGywLbqmigxE4Qlvuw' --videoid='4l-yByZpaaM'
'''

from youtube_services import get_authenticated_service, get_comments, word_count, json_save

from googleapiclient.discovery import HttpError

import os

if __name__ == "__main__":
  folders = os.listdir('resources/targets')
  for folder in folders:
    fileName = os.path.join('resources/targets', folder) 
    with open(fileName, 'r') as f:
      channel_id = folder  # folder is YouTube channelId
      video_id = f.readline()
      youtube = get_authenticated_service(channel_id, video_id)

      try:
        # Insert channel comment by omitting videoId
        """ insert_comment(youtube, args.channelid, None, args.text) """
        # Insert video comment
        """ insert_comment(youtube, args.channelid, args.videoid, args.text) """

        # Video comments
        print("===== Video Comments ======")
        video_comments = get_comments(youtube, video_id, None)
        json_save(from_list=video_comments, video_id=video_id, channel_id=channel_id)

        """ if video_comments:
          update_comment(youtube, video_comments[0]) """

        # Channel comments
        print("===== Channel Comments ======")
        channel_comments = get_comments(youtube, None, channel_id)
        json_save(from_list=video_comments, video_id=video_id, channel_id=channel_id)

        """ if channel_comments:
          update_comment(youtube, channel_comments[0]) """

      except HttpError:
        print("An HTTP error {} occurred:\n{}".format(
            HttpError.uri, HttpError.error_details))
      else:
        print("*** No Inserted, no updated, but got top-level comments. :) ***")
