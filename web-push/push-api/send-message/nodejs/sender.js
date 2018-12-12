const webpush = require('web-push');

// VAPID keys should only be generated only once.
//const vapidKeys = webpush.generateVAPIDKeys();
const publicVapidKey = 'BLlTN2XJ_ajKuYxFHLAGo4LwTHdQ0IVHsvjMM_iZ9i_6gZxn6ucv4klQZMgP7QE2RML_ghfmM75DjCvJQa9T0LM';
const privateVapidKey = 'tD5-DlFVgxP9y8VuRxh5Hc_6Ntg39m6T3hfzxuCnJ1U';

//webpush.setGCMAPIKey('<Your GCM API Key Here>');
webpush.setGCMAPIKey('AAAAewpzBDU:APA91bHU9nih4Ha6KJMv9s95XgMCCPgW8YIV5tdaYDGQUcTjFujbQt4LlLE6n8E7MhvSqETraxAeSqYCBG_ept8V3-zAc4fj-J3H62fnyxLkj5YOFsxRj9kBAh6G8k4-A271TKP05AoE');
webpush.setVapidDetails('mailto:jhkim@spectra.co.kr', publicVapidKey, privateVapidKey);

// Get pushSubscription object
const subscription = {"endpoint":"https://fcm.googleapis.com/fcm/send/elaDONlXo-E:APA91bGSGsG_YSDNpUEJNHEGxs4mFwW7dXWAQowPsXXQ57wvo0YY_4oqj0Sj9CkJayk-nB79FPVeLsqS6mOeoPsWhwgQRYka5EKu5XQ2Tg1wDOYL4ybCNSPCU2Y2ja_8HuGneipY7dZJ","expirationTime":null,"keys":{"p256dh":"BN1ypzhRrJY811r9b978yYccMl9sAV0Bg3_L6Z5rzxZ6PLmTxp2A6HZO0A0ySDNcrMZUC3mke-YK_l3bZbB0PlU","auth":"EB7VcLyrghL9T8n-AT3ucQ"}};

// Create payload
const payload = JSON.stringify({ title: 'Push Test' });

// Pass object into sendNotification
webpush.sendNotification(subscription, payload).catch(err => console.error(err));
