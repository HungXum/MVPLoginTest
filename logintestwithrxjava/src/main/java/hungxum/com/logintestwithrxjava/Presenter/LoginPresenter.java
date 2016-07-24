package hungxum.com.logintestwithrxjava.Presenter;

import hungxum.com.logintestwithrxjava.Model.Bean.User;
import hungxum.com.logintestwithrxjava.Model.IModel.ILoginModel;
import hungxum.com.logintestwithrxjava.Model.LoginModel;
import hungxum.com.logintestwithrxjava.Presenter.IPresenter.ILoginPresenter;
import hungxum.com.logintestwithrxjava.View.IView.ILoginView;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hung_Xum on 2016/7/24.
 */
public class LoginPresenter implements ILoginPresenter{
    private ILoginModel loginModel;
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView){
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    @Override
    public void Login() {
        loginModel.login(loginView.getUser())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        //事件发布后的处理逻辑
//                        loginView.showSuccessInfo(user);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        //获取某一事件的处理逻辑
                        if (user.getName().equals("hungxum") && user.getPassword().equals("123456")){
                            loginView.showSuccessInfo(user);
                        }else {
                            loginView.showFailInfo();
                        }
                    }
                });
    }
}
