# Chatbot
Build your own chatbot on the Innovation Day

[![Watson: Conversation](https://img.shields.io/badge/watson-conversation-5596e6.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Speech-to-Text](https://img.shields.io/badge/watson-speech--to--text-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Text-to-Speech](https://img.shields.io/badge/watson-text--to--speech-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/CognitiveBuild/Chatbot/master/LICENSE)

# Prerequisite
* Register your [Bluemix](https://bluemix.net) account
* [Install VirtualBox](https://github.com/yidlhu/blockchaindemo/blob/master/GuideForVirtualBoxVM.MD)
* [Complete Node-Red and Blockchain practice](https://github.com/yidlhu/blockchaindemo/blob/master/README.md)

# Chatbot practice (2017 Innovation Day)
- [Configure and Run the Chatbot on Virtual Machine](docs/VM.md) (Provided image on Innovation Day only)

# Setup guide for the other environments
### Docker (Choose one)
- [Host your Chatbot on Docker](docs/CHATBOT.md)
- [Host your Chatbot on Docker (Pre-built image)](docs/SHELL.md)

### Cloud / Local Deployment
- [Setup guide for Bluemix](docs/BLUEMIX.md)
- [Setup guide for local machine](docs/LOCAL.md)

# Dependencies
* Apache Common Codec
* Apache HTTP Client
* FastJSON
* Watson Java SDK
* Watson Speech JavaScript SDK

# Issues
* Watson JavaScript Speech SDK does not support **Safari**
* The way of obtaining token is not secured, do not use it on Production

# Sample data for Conversation
**Ice cream flavour entities**
* Chocolate
* Vanilla
* Coffee
* Mango
* Strawberry
* Black sesame
* Red beans

# License
Copyright 2017 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
