# Tatai

This project is a gamified, speech recognition learning tool for the Maori language. 
In order for a native tongue to continue to exist, resources must exist to promote its usage.
The competitive multiplayer and single player modes help to improve an individuals understanding
of the language in an enjoyable way. 

THIS PROJECT WILL NO LONGER RUN - AZURE SUPPORT HAS BEEN DISCONTINUED.

##Overview / Important Features

This project has designed to demonstrate understanding of design patterns and software
architecture, while also providing a meaningful outcome.
The following sections will give an overview of the important project components.

###Online Functionality

The Tatai application features online fuctionality implemented through Azure web services.
3 Tier Architecture has been implemented using SQL Server and a SOAP API deployed on the 
Azure cloud, which provides functionality for multiplayer gameplay, user management,
leaderboards and more.
 
###Gameplay

The Tatai game involves 10 question levels, each level providing you a number to 
pronounce in the Maori language. The HTK speech recognition software determines whether you have
correctly pronounced the number or not. This allows users to beat personal best scores 
and learn numeracy in Maori. Additionally, you may play a static multiplayer mode against 
other users where both players are given the same random set of 10 numbers to pronounce. 

###Security

There have been some security measures considered (that are not encryption based).
Server-side processing has been used as much as possible to prevent tampering with client
game data, making it impossible to illegally alter multiplayer scores.
An important feature here is that all SPEECH/AUDIO data is sent as a byte stream to the
Azure API, where the speech recognition software is hosted. This also prevents the illegal distribution
of the HTK software.  
