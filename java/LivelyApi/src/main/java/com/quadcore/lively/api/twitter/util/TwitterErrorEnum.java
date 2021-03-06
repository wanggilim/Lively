package com.quadcore.lively.api.twitter.util;

public enum TwitterErrorEnum {

//	3	Invalid coordinates.
//	Corresponds with HTTP 400. The coordinates provided as parameters were not valid for the request.
//	13	No location associated with the specified IP address.
//	Corresponds with HTTP 404. It was not possible to derive a location for the IP address provided as a parameter on the geo search request.
//	17	No user matches for specified terms.
//	Corresponds with HTTP 404. It was not possible to find a user profile matching the parameters specified.
//	32	Could not authenticate you	Corresponds with HTTP 401. There was an issue with the authentication data for the request.
//	34	Sorry, that page does not exist	Corresponds with HTTP 404. The specified resource was not found.
//	36	You cannot report yourself for spam.	Corresponds with HTTP 403. You cannot use your own user ID in a report spam call.
//	44	attachment_url parameter is invalid	Corresponds with HTTP 400. The URL value provided is not a URL that can be attached to this Tweet.
//	50	User not found.	Corresponds with HTTP 404. The user is not found.
//	63	User has been suspended.	Corresponds with HTTP 403 The user account has been suspended and information cannot be retrieved.
//	64	Your account is suspended and is not permitted to access this feature	Corresponds with HTTP 403. The access token being used belongs to a suspended user.
//	68	The Twitter REST API v1 is no longer active. Please migrate to API v1.1.	Corresponds to a HTTP request to a retired v1-era URL.
//	87	Client is not permitted to perform this action.	Corresponds with HTTP 403. The endpoint called is not a permitted URL.
//	88	Rate limit exceeded	The request limit for this resource has been reached for the current rate limit window.
//	89	Invalid or expired token	The access token used in the request is incorrect or has expired.
//	92	SSL is required	Only SSL connections are allowed in the API. Update the request to a secure connection. See how to connect using TLS
//	93	This application is not allowed to access or delete your direct messages	Corresponds with HTTP 403. The OAuth token does not provide access to Direct Messages.
//	99	Unable to verify your credentials.	Corresponds with HTTP 403. The OAuth credentials cannot be validated. Check that the token is still valid.
//	120	Account update failed: value is too long (maximum is nn characters).
//	Corresponds with HTTP 403. Thrown when one of the values passed to the update_profile.json endpoint exceeds the maximum value currently permitted for that field. The error message will specify the allowable maximum number of nn characters.
//	130	Over capacity	Corresponds with HTTP 503. Twitter is temporarily over capacity.
//	131	Internal error	Corresponds with HTTP 500. An unknown internal error occurred.
//	135	Could not authenticate you	Corresponds with HTTP 401. Timestamp out of bounds (often caused by a clock drift when authenticating - check your system clock)
//	139	You have already favorited this status.	Corresponds with HTTP 403. A Tweet cannot be favorited (liked) more than once.
//	144	No status found with that ID.	Corresponds with HTTP 404. The requested Tweet ID is not found (if it existed, it was probably deleted)
//	150	You cannot send messages to users who are not following you.	Corresponds with HTTP 403. Sending a Direct Message failed.
//	151	There was an error sending your message: reason	Corresponds with HTTP 403. Sending a Direct Message failed. The reason value will provide more information.
//	160	You've already requested to follow user.
//	Corresponds with HTTP 403. This was a duplicated follow request and a previous request was not yet acknowleged.
//	161	You are unable to follow more people at this time	Corresponds with HTTP 403. Thrown when a user cannot follow another user due to some kind of limit
//	179	Sorry, you are not authorized to see this status	Corresponds with HTTP 403. Thrown when a Tweet cannot be viewed by the authenticating user, usually due to the Tweet’s author having protected their Tweets.
//	185	User is over daily status update limit	Corresponds with HTTP 403. Thrown when a Tweet cannot be posted due to the user having no allowance remaining to post. Despite the text in the error message indicating that this error is only thrown when a daily limit is reached, this error will be thrown whenever a posting limitation has been reached. Posting allowances have roaming windows of time of unspecified duration.
//	186	Tweet needs to be a bit shorter.	Corresponds with HTTP 403. The status text is too long.
//	187	Status is a duplicate	The status text has already been Tweeted by the authenticated account.
//	205	You are over the limit for spam reports.	Corresponds with HTTP 403. The account limit for reporting spam has been reached. Try again later.
//	215	Bad authentication data	Corresponds with HTTP 400. The method requires authentication but it was not presented or was wholly invalid.
//	220	Your credentials do not allow access to this resource.	Corresponds with HTTP 403. The authentication token in use is restricted and cannot access the requested resource.
//	226	This request looks like it might be automated. To protect our users from spam and other malicious activity, we can’t complete this action right now.	We constantly monitor and adjust our filters to block spam and malicious activity on the Twitter platform. These systems are tuned in real-time. If you get this response our systems have flagged the Tweet or Direct Message as possibly fitting this profile. If you believe that the Tweet or DM you attempted to create was flagged in error, report the details by filing a ticket at https://help.twitter.com/forms/platform.
//	231	User must verify login	Returned as a challenge in xAuth when the user has login verification enabled on their account and needs to be directed to twitter.com to generate a temporary password. Note that xAuth is no longer an available option for authentication on the API.
//	251	This endpoint has been retired and should not be used.	Corresponds to a HTTP request to a retired URL.
//	261	Application cannot perform write actions.	Corresponds with HTTP 403. Thrown when the app is restricted from POST, PUT, or DELETE actions. Check the information on your app dashboard. You may also file a ticket at https://help.twitter.com/forms/platform.
//	271	You can’t mute yourself.	Corresponds with HTTP 403. The authenticated user account cannot mute itself.
//	272	You are not muting the specified user.	Corresponds with HTTP 403. The authenticated user account is not muting the account a call is attempting to unmute.
//	323	Animated GIFs are not allowed when uploading multiple images.	Corresponds with HTTP 400. Only one animated GIF may be attached to a single Tweet.
//	324	The validation of media ids failed.	Corresponds with HTTP 400. There was a problem with the media ID submitted with the Tweet.
//	325	A media id was not found.	Corresponds with HTTP 400. The media ID attached to the Tweet was not found.
//	326	To protect our users from spam and other malicious activity, this account is temporarily locked.	Corresponds with HTTP 403. The user should log in to https://twitter.com to unlock their account before the user token can be used.
//	327	You have already retweeted this Tweet.
//	Corresponds with HTTP 403. The user cannot retweet the same Tweet more than once. 
//	354	The text of your direct message is over the max character limit.	Corresponds with HTTP 403. The message size exceeds the number of characters permitted in a Direct Message.
//	385	You attempted to reply to a Tweet that is deleted or not visible to you.	Corresponds with HTTP 403. A reply can only be sent with reference to an existing public Tweet.
//	386	The Tweet exceeds the number of allowed attachment types.	Corresponds with HTTP 403. A Tweet is limited to a single attachment resource (media, Quote Tweet, etc.)
//	407	The given URL is invalid.	Corresponds with HTTP 400. A URL included in the Tweet could not be handled. This may be because a non-ASCII URL could not be converted, or for other reasons.
//	415	Callback URL not approved for this client application. Approved callback URLs can be adjusted in your application settings
//	Corresponds with HTTP 403. The app callback URLs must be whitelisted via the app details page in the developer portal. Only approved callback URLs may be used by the Twitter app. See the Callback URL documentation.
//	416	Invalid / suspended application
//	Corresponds with HTTP 401. The app has been suspended and cannot be used with Sign-in with Twitter.
//	417	Desktop applications only support the oauth_callback value 'oob'
//	Corresponds with HTTP 401. The application is attempting to use out-of-band PIN-based OAuth, but a callback URL has been specified in the app settings.
}
