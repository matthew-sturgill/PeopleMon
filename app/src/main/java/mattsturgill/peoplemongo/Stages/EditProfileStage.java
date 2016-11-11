package mattsturgill.peoplemongo.Stages;

import android.app.Application;

import com.davidstemmer.screenplay.stage.Stage;

import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Riggers.SlideRigger;

/**
 * Created by matthewsturgill on 11/11/16.
 */

public class EditProfileStage extends IndexedStage{
    private final SlideRigger rigger;

    public EditProfileStage(Application context){
        super(PeopleMonListStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public EditProfileStage(){
        this(PeopleMonGoApplication.getInstance());

    }

    @Override
    public int getLayoutId() {
        return R.layout.edit_profile_view;
    }

    @Override
    public Stage.Rigger getRigger() {
        return rigger;
    }
}
