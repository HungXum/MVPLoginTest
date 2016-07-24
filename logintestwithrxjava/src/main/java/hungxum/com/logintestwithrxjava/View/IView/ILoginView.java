package hungxum.com.logintestwithrxjava.View.IView;

import hungxum.com.logintestwithrxjava.Model.Bean.User;

/**
 * Created by Hung_Xum on 2016/7/24.
 */
public interface ILoginView {
    User getUser();
    void showSuccessInfo(User user);
    void showFailInfo();
}
