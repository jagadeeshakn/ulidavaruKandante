package com.conflux.finflux.offline.data;

import com.conflux.finflux.collectionSheet.data.Center;
import com.conflux.finflux.util.Logger;

/**
 * Created by Praveen J U on 8/3/2016.
 */
public class TypesEnum {
    private final String TAG = getClass().getSimpleName();
    public enum Entity {
        Status(1),Center(2),Group(3);
        private int value;
        private Entity(int value){
            this.value = value;
        }
    }

    Entity entity;

    public TypesEnum(Entity entity) {
        this.entity = entity;
    }

    public int entityDetails() {
        int typeId = 0;
        switch (entity) {
            case Status:
                typeId =1;
                break;

            case Center:
                typeId = 2;
                break;

            case Group: typeId =3;
                break;

            default:
                Logger.e(TAG,"enum not matched");
                break;
        }
        return typeId;
    }
}
