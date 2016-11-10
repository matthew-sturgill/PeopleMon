package mattsturgill.peoplemongo.Stages;

import android.app.Application;

import com.davidstemmer.screenplay.stage.Stage;

import mattsturgill.peoplemongo.PeopleMonGoApplication;
import mattsturgill.peoplemongo.R;
import mattsturgill.peoplemongo.Riggers.SlideRigger;

/**
 * Created by matthewsturgill on 11/8/16.
 */

public class MapStage extends IndexedStage{
    private final SlideRigger rigger;

    public MapStage(Application context){
        super(MapStage.class.getName());
        this.rigger = new SlideRigger(context);
    }

    public MapStage(){
        this(PeopleMonGoApplication.getInstance());

    }

    @Override
    public int getLayoutId() {
        return R.layout.map_view;
    }

    @Override
    public Stage.Rigger getRigger() {
        return rigger;
    }

}

