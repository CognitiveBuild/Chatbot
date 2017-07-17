# Chatbot
Build your own chatbot on the Innovation Day

[![Watson: Conversation](https://img.shields.io/badge/watson-conversation-5596e6.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Speech-to-Text](https://img.shields.io/badge/watson-speech--to--text-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![Watson: Text-to-Speech](https://img.shields.io/badge/watson-text--to--speech-994fd7.svg?style=flat)](https://github.com/CognitiveBuild/Chatbot)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/CognitiveBuild/Chatbot/master/LICENSE)

# Prerequisite
* Register your [Bluemix](https://bluemix.net) account
* Register your [Github](https://github.com) account 
* Fork this repository and remember the link of forked application

	<img width="730" alt="Fork GitHub" src="https://cloud.githubusercontent.com/assets/1511528/20171945/01edad1a-a76e-11e6-83ed-3cd0245ae2d2.png">

	<img width="730" alt="Forked result" src="https://cloud.githubusercontent.com/assets/1511528/20475569/7f60d6cc-b007-11e6-870b-87ae0d7b238f.png">

# Three ways for deployment

- [Hosting Chatbot on docker](docs/DOCKER.md)
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
Copyright 2016 GCG GBS CTO Office under [the Apache 2.0 license](LICENSE).
