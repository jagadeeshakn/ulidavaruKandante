package com.conflux.finflux.offline.data;

import android.content.Context;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.ExtendedProductionCollectiondata;
import com.conflux.finflux.collectionSheet.data.MeetingFallCenter;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.db.collectionsheet.TableMeetingFallCenter;
import com.conflux.finflux.util.Logger;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Praveen J U on 7/18/2016.
 */
public class ProductionCollectionSheedDataAssembler {
    List<ProductiveCollectionData> productiveCollectionDatas;
    ArrayList<ExtendedProductionCollectiondata> extendedProductionCollectiondatas;
    private final String meetingDate;
    private int numberOfCenters;
    private Realm realm;
    private Context context;

    public ProductionCollectionSheedDataAssembler(final Context context, final ArrayList<ProductiveCollectionData> productiveCollectionDatas, final String meetingDate){
        this.context = context;
        this.productiveCollectionDatas = productiveCollectionDatas;
        this.meetingDate = meetingDate;
    }

    public ArrayList<ExtendedProductionCollectiondata> assembleProductionCollectionSheetData(){
            extendedProductionCollectiondatas = new ArrayList<ExtendedProductionCollectiondata>();
            for(ProductiveCollectionData productiveCollectionData : productiveCollectionDatas){
                numberOfCenters = productiveCollectionData.getMeetingFallCenters().size();
                ExtendedProductionCollectiondata extendedProductionCollectiondata = new ExtendedProductionCollectiondata(productiveCollectionData, meetingDate, numberOfCenters);
                extendedProductionCollectiondatas.add(extendedProductionCollectiondata);
            }
            return extendedProductionCollectiondatas;
    }

    public ArrayList<CenterListHelper> assembleCenterListHelperData(Realm realm){
        this.realm = realm;
        ArrayList<CenterListHelper> centerListHelpers = new ArrayList<>();
        for(ProductiveCollectionData productiveCollectionData : productiveCollectionDatas){
            for(MeetingFallCenter meetingFallCenter : productiveCollectionData.getMeetingFallCenters()){
                CenterListHelper centerListHelper = new CenterListHelper();
                centerListHelper.setMeetingFallCenter(meetingFallCenter);
                centerListHelper.setDate(meetingDate);
                if(!isCenterWithIdAlreadyExist(meetingFallCenter.getId())){
                    centerListHelper.setCanDownload(false);
                    centerListHelper.setReason(context.getString(R.string.center_download_status_already_downloaded));
                }else if(meetingFallCenter.getTotaldue() == 0 ){
                    centerListHelper.setCanDownload(false);
                    centerListHelper.setReason(context.getString(R.string.center_download_status_center_no_collection));
                }else if(meetingFallCenter.getTotalCollected() != 0){
                    centerListHelper.setCanDownload(false);
                    centerListHelper.setReason(context.getString(R.string.center_download_status_center_collection_completed));
                }else {
                    centerListHelper.setCanDownload(true);
                    centerListHelper.setReason(context.getString(R.string.collection_status_can_download));
                }
                centerListHelpers.add(centerListHelper);
            }

        }
        return centerListHelpers;
    }

    private boolean isCenterWithIdAlreadyExist(final Long centerId){
        long count = realm.where(TableMeetingFallCenter.class).equalTo("id",centerId).count();
        if(count > 0){
            return true;
        }else {
            return false;
        }
    }

}
