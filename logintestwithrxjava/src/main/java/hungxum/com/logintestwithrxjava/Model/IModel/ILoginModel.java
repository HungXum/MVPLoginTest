package hungxum.com.logintestwithrxjava.Model.IModel;


import hungxum.com.logintestwithrxjava.Model.Bean.User;
import rx.Observable;

/**
 * Created by Hung_Xum on 2016/7/24.
 */
public interface ILoginModel {

//    //model类操作成功的回调，在Presenter中实现，用于成功和失败时更新View
//    public interface LoginRequestCallBack{
//        void LoginSuccess(User user);
//        void LoginFail();
//    }

    Observable<User> login(User user);
}
