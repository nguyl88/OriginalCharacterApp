# OriginalCharacterApp
CSE248 Project Idea
### OriginalCharacterApp ###
This is my app that is going to be based on a user create and post a character with information. It's inspired by social media.
### Main Features ###
-Login, Register, upload user image
-Firebase API (For database and network)
-Storage reference 
-Likes feature
-Upload character with information
-Share feature
-Edit character information feature
-Change user profile image
-Search User feature (Since it's a small server, I won't put hashtags)
-Firebase Auth
-Settings feature (User can change password, username, email, delete account and delete characters)
-Website test -> https://occreations-b6c14.firebaseapp.com/ (Something extras)

# Use Case Diagram #
![alt text](https://github.com/nguyl88/OriginalCharacterApp/blob/master/documents/CSE248%20Project%20UseCaseDiagram.png)

# Model Diagram #
![alt text](https://github.com/nguyl88/OriginalCharacterApp/blob/master/documents/CSE248%20Project%20Class%20Diagram.png)

#Burndown Chart#
https://github.com/nguyl88/OriginalCharacterApp/blob/master/documents/CSE248%20Burndown%20Chart%20Project.docx

# Firebase Database Table #
[Imgur](https://i.imgur.com/Aiixyut.png)

The design pattern is Model View Controller. The model are the users, characters, and likes (not implemented yet). The view are the interfaces which are the xml files. The bottom navigation view holds five different fragments to display different activities for the user to interact. The fragment IS an activity, but they are different than activity, but much complicated to implement. 

# Fragments # 
 A Fragment represents a behavior or a portion of user interface in a FragmentActivity. You can combine multiple fragments in a single activity to build a multi-pane UI and reuse a fragment in multiple activities. You can think of a fragment as a modular section of an activity, which has its own lifecycle, receives its own input events, and which you can add or remove while the activity is running (sort of like a "sub activity" that you can reuse in different activities).
" ~~~~Android Developer Documentations

# --- Inspiration --- #
-Character Planner App (from google play store), Instagram Clone (CodingWithMitch), PhotoBlog, charahub.com

 # Other features that I would like to add but no time for  #
 -Comment feature
 -Hashtag feature (to search for characters instead of users) and the entire post would change by adding a username with likes.
 -Follow other user feature
 -Notification feature

 
