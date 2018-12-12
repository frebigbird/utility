#!/bin/sh

# hosting : dGcroihHFCk:APA91bFz6lqbbwBTwJfA_GPJQyIg0X72l7Zh5t2mFL_myqn-K6ulL3-eISXHXGo0WLbHNZqp7hUQflCGSVgjdIAtaiNK3z_fv5r19v5_my2YR4sLAwo2s-Jrh_zIv9X-JAHB_4u41Zdg
# nodejs  : c-2mGXwRl2U:APA91bFYiWxsg_dmh2dBQ565faiHxmNrVm_7cYP_92rOAA8jYVhNRmklMb1Qc39e50EhhXAFhQG1sqb4xtzzDD-P_BflzFhekrcalrRZjzISIB1gI5d6dCCOoQlpX26xFLzlvULSKl7M

curl -X POST -H "Authorization: Bearer ya29.c.ElpvBtOE8RxVgjTSNP24_XIqMNyDAADEhwWYIBXmA8zA6zwJ2Fmu0LfLBMS5rqsl2eHB7DhJLDIarXw3S6wirUnmUGPE9js7EUHw4w6DQ0gFRYaKUxvPPDKoEVw" -H "Content-Type: application/json" -d "{ \
'message':{ \
  'notification': { \
    'title': 'FCM Message', \
    'body': 'This is an FCM Message', \
  }, \
  'token': 'c-2mGXwRl2U:APA91bFYiWxsg_dmh2dBQ565faiHxmNrVm_7cYP_92rOAA8jYVhNRmklMb1Qc39e50EhhXAFhQG1sqb4xtzzDD-P_BflzFhekrcalrRZjzISIB1gI5d6dCCOoQlpX26xFLzlvULSKl7M' \
  } \
}" https://fcm.googleapis.com/v1/projects/fcmtest-2d819/messages:send

#curl -X POST -H "Authorization: key=AAAAewpzBDU:APA91bHU9nih4Ha6KJMv9s95XgMCCPgW8YIV5tdaYDGQUcTjFujbQt4LlLE6n8E7MhvSqETraxAeSqYCBG_ept8V3-zAc4fj-J3H62fnyxLkj5YOFsxRj9kBAh6G8k4-A271TKP05AoE" -H "Content-Type: application/json" -d "{ \
#  \"notification\": { \
#    \"title\": \"Portugal vs. Denmark\", \
#    \"body\": \"5 to 1\", \
#    \"icon\": \"firebase-logo.png\", \
#    \"click_action\": \"http://localhost:8081\" \
#  }, \
#  \"to\": \"dGcroihHFCk:APA91bFz6lqbbwBTwJfA_GPJQyIg0X72l7Zh5t2mFL_myqn-K6ulL3-eISXHXGo0WLbHNZqp7hUQflCGSVgjdIAtaiNK3z_fv5r19v5_my2YR4sLAwo2s-Jrh_zIv9X-JAHB_4u41Zdg\" \
#}" https://fcm.googleapis.com/fcm/send
