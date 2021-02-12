### Overview:

The project is written in Kotlin and uses Clean Architecture.
Popular Frameworks have been used i.e. JetPack (Coroutines, LiveData, Room, ViewModel), 
Retrofit, Glide, Dagger, Mockito-kotlin, Mockk.
The application fetches a json feed of items from internet and shows the items in a list.
The items are also saved in local storage. When user clicks an item another screen opens 
showing details of the selected item.

### Description:

The initial screen is the Home Screen which shows a list of Breaking Bad characters with an image 
and brief information for each character. If internet is not connected the items will be 
retrieved from the local storage (Room). Home screen also supports swipe to refresh. 
The next screen is the Item Detail Screen which shows a larger image of the selected character 
and more detailed information about it. 

### Tests:

The project contains Unit Tests and Instrumented Tests. Some utility classes have been used 
for setting up the tests with Coroutines and LiveData. Also some mock network responses 
from real JSON data have been used. The Tests share some common resources which exist under 
the sharedTest folder.

