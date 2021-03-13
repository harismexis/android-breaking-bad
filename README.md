### Overview:

The project is written in Kotlin with Clean Architecture. 
It uses JetPack i.e. Coroutines, LiveData, Room, ViewModel and 
Retrofit, Glide, Dagger, Mockito-kotlin, Mockk etc.

### Description:

The initial screen is the Home Screen which shows a list of Breaking Bad Actors with 
image and brief information for each item. If internet is not connected the items will 
be retrieved from the local storage (Room). Home screen also supports searching actors
by name. The next screen is the Item Detail Screen which shows a larger image of the 
selected actor and more detailed information.  

### Tests:

The project contains Unit Tests and Instrumented Tests.

#### Home
![Alt text](screenshots/home_1.png?raw=true "app screenshot")

#### Item Detail
![Alt text](screenshots/actor_detail_1.png?raw=true "app screenshot")