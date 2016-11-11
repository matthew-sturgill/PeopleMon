package mattsturgill.peoplemongo.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mattsturgill.peoplemongo.R;

/**
 * Created by matthewsturgill on 11/11/16.
 */

public class EditProfileView extends LinearLayout {

    @Bind(R.id.edit_username)
    EditText editUsername;

    @Bind(R.id.picture_button)
    Button pictureButton;

    @Bind(R.id.update_profile)
    Button updateProfile;

    public EditProfileView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.picture_button)
    public void pictureButton(){

    }

    @OnClick(R.id.update_profile)
    public void updateProfileButtonPress(){

    }
}
