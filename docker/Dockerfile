FROM websphere-liberty:webProfile7
ADD payload/Chatbot.war /config/dropins/
COPY payload/server.xml /config/
ARG REPOSITORIES_PROPERTIES=""

RUN if [ ! -z $REPOSITORIES_PROPERTIES ]; then echo $REPOSITORIES_PROPERTIES > /opt/ibm/wlp/etc/repositories.properties; fi \
    && installUtility install --acceptLicense appSecurityClient-1.0 javaee-7.0 javaeeClient-7.0 \
    && if [ ! -z $REPOSITORIES_PROPERTIES ] ; then rm /opt/ibm/wlp/etc/repositories.properties; fi \
    && rm -rf /output/workarea /output/logs
EXPOSE 9080
ENV APPLICATION_API_URL https://blockchainlab.mybluemix.net/api/order/newinfo

ENV TEXT_TO_SPEECH_USERNAME your_username
ENV TEXT_TO_SPEECH_PASSWORD your_password
ENV SPEECH_TO_TEXT_USERNAME your_username
ENV SPEECH_TO_TEXT_PASSWORD your_password
ENV CONVERSATION_USERNAME your_username
ENV CONVERSATION_PASSWORD your_password
ENV CONVERSATION_WORKSPACE_ID your_work_space_id
ENV VISUAL_RECOGNITION_API_KEY your_api_key
ENV VISUAL_RECOGNITION_CLASSIFIER_ID your_classfier_id