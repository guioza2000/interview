package com.hub.gui.wag.manager;

import com.hub.gui.wag.api.StackExchangeClient;
import com.hub.gui.wag.model.ResponseWrapper;
import com.hub.gui.wag.model.User;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The manager responsible for requesting the users.
 * Use {@link #registerListener(UserManagerListener listener) registerListener} to be notified when the request returned.
 */
public class UserManager {

    public static UserManager sInstance;

    //Thread safe Set
    private final Set<UserManagerListener> mListeners = Collections.newSetFromMap(new ConcurrentHashMap<UserManagerListener, Boolean>(0));

    public static UserManager getsInstance(){
        if(sInstance == null)
            sInstance = new UserManager();

        return  sInstance;
    }

    private UserManager(){}

    public void requestUsers(String site, int pageSize){

        Call<ResponseWrapper<User>> call = StackExchangeClient.getService().getUsers(site,pageSize);
        call.enqueue(new Callback<ResponseWrapper<User>>() {
            @Override
            public void onResponse(Call<ResponseWrapper<User>> call, Response<ResponseWrapper<User>> response) {
                notifyListners(response.body());
            }

            @Override
            public void onFailure(Call<ResponseWrapper<User>> call, Throwable t) {
                notifyListners(null);
            }
        });

    }

    /**
     * register a {@link UserManagerListener} that will be notify
     * @param listener
     */
    public void registerListener(UserManagerListener listener){
        if(listener == null)
            return;

        mListeners.add(listener);
    }

    /**
     * unregister a {@link UserManagerListener}
     * @param listener
     */
    public void unregisterListener(UserManagerListener listener){
        if(listener == null)
            return;

        mListeners.remove(listener);
    }

    /**
     * Clear all the {@link UserManagerListener} from the manager
     */
    public void clearListeners(){
        mListeners.clear();
    }

    private void notifyListners(ResponseWrapper<User> users){

        for (UserManagerListener listener: mListeners){
            if(users != null){
                listener.onUserRequestReceived(users);
            }
            else{
                listener.onUserRequestFailed();
            }
        }
    }

    /**
     * The Listener will be called by the {@link UserManager} on the UIThread
     */
    public interface UserManagerListener {

        void onUserRequestReceived(ResponseWrapper<User> response);
        void onUserRequestFailed();

    }
}
