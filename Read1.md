SV2TTS - Transfer Learning from Speaker Verification to Multispeaker Text-To-Speech Synthesis
Voice Cloning tool
Setup and Install
Python 3.7 is needed to run the toolbox
#Clone repository
git clone https://github.com/CorentinJ/Real-Time-Voice-Cloning.git
#Select directory
cd Real-Time-Voice-Cloning/
#Install Pytorch
pip install torch torchvision
#Install requirement and dependencies
pip install -r requirements.txt
#Download pretrained data and unzip it
gdown https://drive.google.com/uc?id=1n1sPXvT34yXFLT47QZA6FIRGrwMeSsZc
unzip pretrained.zip

#For Speech Recognition
pip install SpeechRecognition
pip install pydub
pip install pyaudio

