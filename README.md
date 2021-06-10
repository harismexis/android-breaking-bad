### Technologies:

- Kotlin, MVVM, JetPack, Navigation, Coroutines, LiveData, Room, ViewModel, Retrofit, Glide, Dagger, 
Mockito-kotlin, MockK
- For emitting / observing events (eg. show error message) I use Event & EventObserver as described here:
https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
- Video playback is implemented using the library PierfrancescoSoffritti/android-youtube-player:
https://github.com/PierfrancescoSoffritti/android-youtube-player

### Description:

Home Screen shows a list of Breaking Bad Actors with image and brief information for each one. 
If internet is not available the actors will be retrieved from local storage (Room). Home screen 
also supports searching actors by name. There is a BottomNavigationView which navigates to Quotes, 
Deaths and Episodes screens. There is also a Player screen for loading breaking bad youtube videos.
The project is ongoing so more things will be added / improved in the future.  

### Tests:

The project contains some Unit Tests and Instrumented Tests. 
More Tests will be added. 

### Screenshots:

#### Home
![Alt text](screenshots/home-1.png?raw=true "app screenshot")

![Alt text](screenshots/home-2.png?raw=true "app screenshot")

![Alt text](screenshots/home-3.png?raw=true "app screenshot")

#### Actor Details
![Alt text](screenshots/actor-detail-1.png?raw=true "app screenshot")

#### Quotes
![Alt text](screenshots/quotes-tabs.png?raw=true "app screenshot")

#### Deaths
![Alt text](screenshots/deaths-bb.png?raw=true "app screenshot")

#### Episodes
![Alt text](screenshots/episodes-tabs.png?raw=true "app screenshot")

#### Player
![Alt text](screenshots/player.png?raw=true "app screenshot")

![Alt text](screenshots/player-2.png?raw=true "app screenshot")