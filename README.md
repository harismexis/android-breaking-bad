### Overview:

The project is written in Kotlin with Clean Architecture + MVVM and uses JetPack components 
i.e. Navigation, Coroutines, LiveData, Room, ViewModel. Also popular libraries have been used
i.e. Retrofit, Glide, Dagger, Mockito-kotlin, Mockk etc.

### Description:

The Home Screen shows a list of Breaking Bad Actors with image and brief information for each one. 
If internet is not available the actors will be retrieved from local storage (Room). Home screen 
also supports searching actors by name. There is a BottomNavigationView which navigates to Quotes, 
Deaths and Episodes screens. The project is ongoing so more things will be added in the future.  

### Tests:

The project contains some Unit Tests and Instrumented Tests. 
More Tests will be added. 

#### Home
![Alt text](screenshots/home_1.png?raw=true "app screenshot")

#### Item Detail
![Alt text](screenshots/actor_detail_1.png?raw=true "app screenshot")