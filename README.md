# NoizerAndroidApp
App for music streaming, allows you to listen music on your mobile phone even in a background.
On search screen you can find any track in data base *(the data is hardcoded due to a lack of server)*.
Play track by clicking on it, player will appear and you could control the playback. After that you can close app and the playback will not stop, you would be able to control playback with notification controller.

*Search screen*
![Screenshot_20240907_223833_Noizer](https://github.com/user-attachments/assets/4b2c67b8-49e4-4f69-bd69-f0928d68f190)

*Notification controller*
![Screenshot_20240907_223919_One UI Home](https://github.com/user-attachments/assets/d8909ee8-902e-45b5-86c3-1a82433a0a1d)

The application uses foreground service to play music even if it is closed.

Music playback is implemented though hls protocol, so there is no need to wait for whole track loading, playback will start realy fast. Application uses media3.exoplayer to playback music and display player with controls.

Clean multi-module architecture makes application easy to maintain and add new features

**Instalation**
To use this application, you should:
    - Clone the repository;
    - Open project in android studio;
    - Build .apk file for release;
    - Run this file on your device.
