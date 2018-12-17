# OriginalCharacterApp
CSE248 Project Idea
### OriginalCharacterApp ###
This is my app that is going to be based on Original Character development such as adding the name, species, personality, age, family members, and bio. 
# ---Details---- #
-INTERFACES - Users will have the ability to create an account and login to start creating their own characters into the database.
-DATA STRUCTURE - Firebase Realtime Database
-NETWORKING - Firebase API - including storage, authentication, and database
-Website test -> https://occreations-b6c14.firebaseapp.com/

My website is linked to that information You will only see the details of my projects.

# --- Inspiration --- #
-Character Planner, Instagram Clone (CodingWithMitch), charahub.com

# Use Case Diagram #
![alt text](https://github.com/nguyl88/OriginalCharacterApp/blob/master/documents/CSE248%20Project%20UseCaseDiagram.png)

# Model Diagram #
![alt text](https://github.com/nguyl88/OriginalCharacterApp/blob/master/documents/CSE248%20Project%20Class%20Diagram.png)

#Burndown Chart#
https://github.com/nguyl88/OriginalCharacterApp/blob/master/documents/Burndown%20chart%20for%20OC%20App.docx

# Firebase Database Table #
[Imgur](https://i.imgur.com/Aiixyut.png)

The design pattern is Model View Controller. The model are the users, characters, and likes (not implemented yet). The view are the interfaces which are the xml files. The bottom navigation view holds five different fragments to display different activities for the user to interact. The fragment IS an activity, but they are different than activity, but much complicated to implement. 

"
# Fragments # 
 A Fragment represents a behavior or a portion of user interface in a FragmentActivity. You can combine multiple fragments in a single activity to build a multi-pane UI and reuse a fragment in multiple activities. You can think of a fragment as a modular section of an activity, which has its own lifecycle, receives its own input events, and which you can add or remove while the activity is running (sort of like a "sub activity" that you can reuse in different activities).

A fragment must always be hosted in an activity and the fragment's lifecycle is directly affected by the host activity's lifecycle. For example, when the activity is paused, so are all fragments in it, and when the activity is destroyed, so are all fragments. However, while an activity is running (it is in the resumed lifecycle state), you can manipulate each fragment independently, such as add or remove them. When you perform such a fragment transaction, you can also add it to a back stack that's managed by the activity—each back stack entry in the activity is a record of the fragment transaction that occurred. The back stack allows the user to reverse a fragment transaction (navigate backwards), by pressing the Back button." ~~~~Android Developer Documentations

# User will have to login with email and password plus, a made up username. #

--- Login page: Email, Password, Sign In Button, Register. ---

- Character Creation Page
- Character's Profile 
- Imageview: (for character's faces)
- Name:
- Age:
- Gender:
- Species/Race:
- Family Members: 
- Personality:
- Info/Biography:
 
