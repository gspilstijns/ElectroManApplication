package be.ucll.electromanapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import be.ucll.electromanapplication.database.AppRepository;
import be.ucll.electromanapplication.model.User;

public class UserViewModel extends AndroidViewModel {

    private AppRepository appRepository;

    public UserViewModel(Application application) {
        super(application);
        appRepository = new AppRepository(application);

    }

    public void insert(User user) {
        appRepository.insertUser(user);
    }
    public User findUserByUserName(String username){
        return appRepository.findUserByUserName(username);
    }
}
