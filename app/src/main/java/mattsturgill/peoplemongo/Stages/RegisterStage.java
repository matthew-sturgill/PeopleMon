package mattsturgill.peoplemongo.Stages;

import android.app.Application;

import com.davidstemmer.screenplay.stage.Stage;

import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Riggers.SlideRigger;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class RegisterStage extends IndexedStage{
    private final SlideRigger rigger;

    public RegisterStage(Application context){
        super(RegisterStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public RegisterStage(){
        this(PeopleMonGoApplication.getInstance());

    }

    @Override
    public int getLayoutId() {
        return R.layout.register_view;
    }

    @Override
    public Stage.Rigger getRigger() {
        return rigger;
    }

}


