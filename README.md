# McDonalds-Menu
A screen that fetches data from our Mock McDonalds Menu API and displays the menu

- Communication with server using Retrofit
- Conversion of Json Data received from server using Gson library
- EpoxyRecyclerView to add programmatically every menu item on main screen
- Usage of epoxy carousel to show horizontal list of itens from menu
- SwipeRefreshLayout to allow refreshing info from server
- Usage of a BaseViewModel, allowing all View Models to attach view and work directly with coroutines 