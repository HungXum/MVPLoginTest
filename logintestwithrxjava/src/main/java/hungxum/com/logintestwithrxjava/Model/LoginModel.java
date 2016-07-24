package hungxum.com.logintestwithrxjava.Model;


import android.os.SystemClock;

import hungxum.com.logintestwithrxjava.Model.Bean.User;
import hungxum.com.logintestwithrxjava.Model.IModel.ILoginModel;
import hungxum.com.logintestwithrxjava.URL.URLs;
import hungxum.com.logintestwithrxjava.Utils.HttpUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Hung_Xum on 2016/7/24.
 */
public class LoginModel implements ILoginModel{
    @Override
    public rx.Observable<User> login(final User user) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                //开始做网络请求等耗时操作
                //如果已经订阅了，就回退
                if (subscriber.isUnsubscribed())
                    return;

                SystemClock.sleep(3000);

                //如果自己写回调接口的话就在此处处理，负责此处只做耗时操作，不处理逻辑
//                if (user.getName().equals("hungxum") && user.getPassword().equals("123456")){
//                    loginRequestCallBack.LoginSuccess(user);
//                }else {
//                    loginRequestCallBack.LoginFail();
//                }

                //发布事件通知订阅者
                subscriber.onNext(user);
                //发布事件通知完成
                subscriber.onCompleted();
            }
        });
    }
}
