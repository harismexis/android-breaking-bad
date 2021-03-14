### Overview:

The project is written in Kotlin with Clean Architecture + MVVM and uses JetPack components 
i.e. Navigation, Coroutines, LiveData, Room, ViewModel. Also popular libraries have been used
i.e. Retrofit, Glide, Dagger, Mockito-kotlin, Mockk etc.

### Description:

The Home Screen shows a list of Breaking Bad Actors with image and brief information for each one. 
If internet is not available the actors will be retrieved from local storage (Room). Home screen 
also supports searching actors by name. There is a BottomNavigationView which navigates to Quotes, 
Deaths and Episodes screens. There is also a Player screen for loading breaking bad youtube videos.
The project is ongoing so more things will be added in the future.  

### Tests:

The project contains some Unit Tests and Instrumented Tests. 
More Tests will be added. 

#### Home
![Alt text](screenshots/home_all.png?raw=true "app screenshot")

![Alt text](screenshots/home_filter_by_name.png?raw=true "app screenshot")

#### Actor Detail
![Alt text](screenshots/actor_detail_1.png?raw=true "app screenshot")

#### Quotes
![Alt text](screenshots/quotes_1.png?raw=true "app screenshot")

#### Player
![Alt text](screenshots/player_1.png?raw=true "app screenshot")