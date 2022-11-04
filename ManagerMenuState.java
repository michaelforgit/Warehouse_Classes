public class ManagerMenuState {



    
    public void logout(){
        if ((WareContext.instance()).getLogin() == WareContext.IsClient){ //Error can't be a client -> manager
            (WareContext.instance()).changeState(0); // [2][0] = -2
        }
        else if (WareContext.instance().getLogin() == WareContext.IsClerk){  //Error can't be a clerk -> manager
            (WareContext.instance()).changeState(1); // [2][1] = -2
        }
        else if (WareContext.instance().getLogin() == WareContext.IsManager) {
            //Logout of manager -> login
            (WareContext.instance()).changeState(2); // [2][2] = 3
        }
    }
}

