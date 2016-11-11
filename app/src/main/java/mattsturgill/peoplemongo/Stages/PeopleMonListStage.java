package mattsturgill.peoplemongo.Stages;

import android.app.Application;

import com.davidstemmer.screenplay.stage.Stage;

import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Riggers.SlideRigger;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class PeopleMonListStage extends IndexedStage {
    private final SlideRigger rigger;

    public PeopleMonListStage(Application context){
        super(PeopleMonListStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public PeopleMonListStage(){
        this(PeopleMonGoApplication.getInstance());

    }

    @Override
    public int getLayoutId() {
        return R.layout.peoplemon_list_view;
    }

    @Override
    public Stage.Rigger getRigger() {return rigger;
    }
}
