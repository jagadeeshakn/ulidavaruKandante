package com.conflux.finflux.offline.databaseoperation;

import android.content.Context;

import com.conflux.finflux.R;
import com.conflux.finflux.collectionSheet.data.CollectionSheetData;
import com.conflux.finflux.collectionSheet.data.ExtendedProductionCollectiondata;
import com.conflux.finflux.collectionSheet.data.ProductiveCollectionData;
import com.conflux.finflux.db.collectionsheet.TableCollectionDataJson;
import com.conflux.finflux.db.collectionsheet.TableCollectionMeetingCalendar;
import com.conflux.finflux.db.collectionsheet.TableInteger;
import com.conflux.finflux.db.collectionsheet.TableMeetingFallCenter;
import com.conflux.finflux.db.collectionsheet.TableProductiveCollectionData;
import com.conflux.finflux.db.collectionsheet.TableStatus;
import com.conflux.finflux.offline.data.CenterListHelper;
import com.conflux.finflux.offline.data.TypesEnum;
import com.conflux.finflux.util.RealmAutoIncrement;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Praveen J U on 7/27/2016.
 */
public class DownloadOperations {
    private CollectionSheetData collectionSheetData;
    private Context context;
    private CenterListHelper centerListHelper;
    private ArrayList<ExtendedProductionCollectiondata> extendedProductionCollectiondata;
    private Realm realm;

    public DownloadOperations(Context context, CollectionSheetData collectionSheetData, CenterListHelper centerListHelper, ArrayList<ExtendedProductionCollectiondata> extendedProductionCollectiondata, Realm realm) {
        this.context = context;
        this.collectionSheetData = collectionSheetData;
        this.centerListHelper = centerListHelper;
        this.extendedProductionCollectiondata = extendedProductionCollectiondata;
        this.realm = realm;
    }

    public void save() {
        final String Date = centerListHelper.getDate();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    TableProductiveCollectionData data;
                    data = checkifMeetingdateExit(realm);
                    if (data == null) {
                        data = realm.createObject(TableProductiveCollectionData.class);
                        data.setId(RealmAutoIncrement.getInstance(realm).getNextId(TableProductiveCollectionData.class));
                        data.setMeetingDate(centerListHelper.getDate());
                        data.setStaffId(centerListHelper.getMeetingFallCenter().getStaffId());
                        data.setStaffName(centerListHelper.getMeetingFallCenter().getStaffName());
                    }
                    TableMeetingFallCenter tableMeetingFallCenter = realm.createObject(TableMeetingFallCenter.class);
                    tableMeetingFallCenter.setFkProductiveCollectionSheetDataId(data.getId());
                    tableMeetingFallCenter.setId(centerListHelper.getMeetingFallCenter().getId());
                    tableMeetingFallCenter.setName(centerListHelper.getMeetingFallCenter().getName());
                    tableMeetingFallCenter.setAccountNo(centerListHelper.getMeetingFallCenter().getAccountNo());
                    tableMeetingFallCenter.setOfficeId(centerListHelper.getMeetingFallCenter().getOfficeId());
                    tableMeetingFallCenter.setStaffId(centerListHelper.getMeetingFallCenter().getStaffId());
                    tableMeetingFallCenter.setStaffName(centerListHelper.getMeetingFallCenter().getStaffName());
                    tableMeetingFallCenter.setHierarchy(centerListHelper.getMeetingFallCenter().getHierarchy());
                    tableMeetingFallCenter.setActive(centerListHelper.getMeetingFallCenter().getActive());
                    tableMeetingFallCenter.setTotaldue(centerListHelper.getMeetingFallCenter().getTotaldue());
                    tableMeetingFallCenter.setTotalOverdue(centerListHelper.getMeetingFallCenter().getTotalOverdue());
                    tableMeetingFallCenter.setTotalCollected(centerListHelper.getMeetingFallCenter().getTotalCollected());

                    TableStatus status = realm.createObject(TableStatus.class);
                    status.setFkCenterId(tableMeetingFallCenter.getId());
                    status.setId(centerListHelper.getMeetingFallCenter().getStatus().getId());
                    status.setCode(centerListHelper.getMeetingFallCenter().getStatus().getCode());
                    status.setValue(centerListHelper.getMeetingFallCenter().getStatus().getValue());
                    status.setType(new TypesEnum(TypesEnum.Entity.Status).entityDetails());
                    tableMeetingFallCenter.setStatus(status);

                    String activationDate = null;
                    for (Integer date : centerListHelper.getMeetingFallCenter().getActivationDate()) {
                        if (activationDate == null) {
                            activationDate = String.valueOf(date);
                        } else {
                            activationDate = activationDate + "/" + String.valueOf(date);
                        }
                    }
                    tableMeetingFallCenter.setActivationDate(activationDate);


                    TableCollectionMeetingCalendar collectionMeetingCalendar = realm.createObject(TableCollectionMeetingCalendar.class);
                    collectionMeetingCalendar.setId(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getId());
                    collectionMeetingCalendar.setCalendarInstanceId(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getCalendarInstanceId());
                    collectionMeetingCalendar.setEntityId(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getEntityId());
                    TableStatus entityType = realm.createObject(TableStatus.class);
                    entityType.setFkCenterId(tableMeetingFallCenter.getId());
                    entityType.setId(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getEntityType().getId());
                    entityType.setCode(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getEntityType().getCode());
                    entityType.setValue(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getEntityType().getValue());
                    entityType.setType(new TypesEnum(TypesEnum.Entity.Center).entityDetails());
                    collectionMeetingCalendar.setEntityType(entityType);
                    collectionMeetingCalendar.setTitle(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getTitle());
                    if (centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getLocation() != null)
                        collectionMeetingCalendar.setLocation(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getLocation());
                    collectionMeetingCalendar.setRepeating(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().isRepeating());
                    collectionMeetingCalendar.setRecurrence(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getRecurrence());
                    String startDate = null;
                    for (Integer date : centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getStartDate()) {
                        if (startDate == null) {
                            startDate = String.valueOf(date);
                        } else {
                            startDate = startDate + "/" + String.valueOf(date);
                        }
                    }
                    collectionMeetingCalendar.setStartDate(startDate);
                    if (centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getMeetingTime().getiLocalMillis() != null)
                        collectionMeetingCalendar.setiLocalMillis(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getMeetingTime().getiLocalMillis());
                    if (centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getMeetingTime().getiChronology() != null) {
                        if (centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getMeetingTime().getiChronology().getiBase() != null) {
                            collectionMeetingCalendar.setiMinDaysInFirstWeek(centerListHelper.getMeetingFallCenter().getCollectionMeetingCalendar().getMeetingTime().getiChronology().getiBase().getiMinDaysInFirstWeek());
                        }
                    }
                    tableMeetingFallCenter.setCollectionMeetingCalendar(collectionMeetingCalendar);

                    TableCollectionDataJson collectionDataJson = realm.createObject(TableCollectionDataJson.class);
                    Gson gson = new Gson();
                    String dataAsJson = gson.toJson(collectionSheetData).toString();
                    collectionDataJson.setFkCenterId(tableMeetingFallCenter.getId());
                    collectionDataJson.setCollectionDataJson(dataAsJson);

                    centerListHelper.setCanDownload(false);
                    centerListHelper.setReason(context.getString(R.string.center_download_status_downloaded_successfully));
                }catch (Exception e){
                    centerListHelper.setReason(context.getString(R.string.center_download_status_downloaded_successfully));
                }
            }

            private TableProductiveCollectionData checkifMeetingdateExit(Realm realm) {
                TableProductiveCollectionData data = realm.where(TableProductiveCollectionData.class).contains("meetingDate", Date).findFirst();
                return data;
            }
        });

    }
}
