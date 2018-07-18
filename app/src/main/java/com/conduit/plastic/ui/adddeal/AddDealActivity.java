package com.conduit.plastic.ui.adddeal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.entity.ProductNameEntity;
import com.conduit.plastic.entity.SpecEntity;
import com.conduit.plastic.entity.standar.StandardBean;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.area.AreaActivity;
import com.conduit.plastic.ui.brand.BrandActivity;
import com.conduit.plastic.ui.productname.ProductNameActivity;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.conduit.plastic.view.banner.BannerView;
import com.conduit.plastic.view.flowlayout.FlowLayout;
import com.conduit.plastic.view.flowlayout.TagAdapter;
import com.conduit.plastic.view.flowlayout.TagFlowLayout;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class AddDealActivity extends BaseFrameActivity<AddDealPresenter, AddDealModel> implements AddDealContract.View {

    @BindView(R.id.main_banners)
    BannerView mainBanners;
    @BindView(R.id.brand_edit)
    EditText brandEdit;
    @BindView(R.id.brand_btn)
    Button brandBtn;
    @BindView(R.id.product_edit)
    EditText productEdit;
    @BindView(R.id.product_btn)
    Button productBtn;
    @BindView(R.id.main_standard)
    TagFlowLayout standardTag;
    @BindView(R.id.main_quality_txt)
    TextView mainQualityTxt;
    @BindView(R.id.main_quality_two)
    Button qualityTwo;
    @BindView(R.id.main_quality)
    TagFlowLayout qualityTag;
    @BindView(R.id.main_spec)
    TagFlowLayout specTag;
    @BindView(R.id.total_edit_txt)
    TextView totalEditTxt;
    @BindView(R.id.total_edit)
    EditText totalEdit;
    @BindView(R.id.desc_edit_txt)
    TextView descEditTxt;
    @BindView(R.id.desc_edit)
    EditText descEdit;
    @BindView(R.id.stock_edit_txt)
    TextView stockEditTxt;
    @BindView(R.id.stock_edit)
    EditText stockEdit;
    @BindView(R.id.main_search)
    Button mainSearch;
    List<String> strings = new ArrayList<>();
    LayoutInflater mInflater;
    boolean isChinaN = false;
    String[] texture;
    String[] stock;
    String[] standard;
    int page = 0;
    SpecEntity specEntity;
    List<StandardBean> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    private MaterialDialog.Builder mLoadDialog;


    @Override
    public void initData() {
        super.initData();
        specList();
        texture = getResources().getStringArray(R.array.texture_array);
        standard = getResources().getStringArray(R.array.standard_array);
        stock = getResources().getStringArray(R.array.stock_array);
        mLoadDialog = new MaterialDialog.Builder(this);
        initLoadDialog();
    }

    private void initLoadDialog() {
        mLoadDialog.title("提示")
                .content("发布中，请稍后")
                .progress(true, 0);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_add_deal;
    }

    @Override
    public void initView() {
        mInflater = LayoutInflater.from(this);
        setBackTxt("返回");
        setTitleTxt("发布处理产品");
        qualityTag.setAdapter(new TagAdapter<StandardBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, StandardBean standardBean) {
                TextView textView = null;
                if (!TextUtils.isEmpty(standardBean.getSpecName1())) {

                    textView = (TextView) mInflater.inflate(R.layout.tag_layout, parent, false);
                    if (isChinaN) {
                        if (TextUtils.isEmpty(standardBean.getSpecName2())) {
                            textView.setText(standardBean.getSpecName1());
                        } else {
                            textView.setText(standardBean.getSpecName1() + "/" + standardBean.getSpecName2());
                        }
                    } else {
                        textView.setText(standardBean.getSpecName1());
                    }
                }
                return textView;
            }


        });
        specTag.setAdapter(new TagAdapter<String>(texture) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = null;
                if (!TextUtils.isEmpty(s)) {
                    textView = (TextView) mInflater.inflate(R.layout.tag_layout, parent, false);
                    textView.setText(s);
                }
                return textView;
            }
        });
        qualityTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                StandardBean standardBean = (StandardBean) qualityTag.getAdapter().getItem(position);
                if (map.containsKey("specId")) {
                    if (map.get("specId").equals(standardBean.getId())) {
                        map.remove("specId");
                    } else {
                        map.put("specId", standardBean.getId());
                    }
                } else {
                    map.put("specId", standardBean.getId());
                }
                return false;
            }
        });
        standardTag.setAdapter(new TagAdapter<String>(standard) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = null;
                if (!TextUtils.isEmpty(s)) {
                    textView = (TextView) mInflater.inflate(R.layout.tag_layout, parent, false);
                    textView.setText(s);
                }
                return textView;
            }
        });
        standardTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                if (map.containsKey("standard")) {
                    if (map.get("standard").equals(String.valueOf((position + 1) * 10))) {

                        map.remove("standard");
                    } else {
                        map.put("standard", String.valueOf((position + 1) * 10));
                        qualityTwo.setSelected(false);
                        initQuality((String) map.get("standard"));
                    }
                } else {
                    map.put("standard", String.valueOf((position + 1) * 10));
                    qualityTwo.setSelected(false);
                    initQuality((String) map.get("standard"));
                }
                return false;
            }
        });
        specTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (map.containsKey("texture")) {
                    if (map.get("texture").equals(String.valueOf((position + 1) * 10))) {
                        map.remove("texture");
                    } else {
                        map.put("texture", String.valueOf((position + 1) * 10));
                    }
                } else {
                    map.put("texture", String.valueOf((position + 1) * 10));
                }
                return false;
            }
        });
    }


    void initQuality(String type) {
        if (map.containsKey("standard")) {
            if ("10".equals(map.get("standard"))) {
                isChinaN = true;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard());
                }
            } else if ("20".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getAmericaStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getAmericaStandard());
                }
            } else if ("30".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getEnglandStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getEnglandStandard());
                }
            } else if ("40".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getJapanStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getJapanStandard());
                }

            }
            qualityTag.getAdapter().notifyDataChanged();
        }
    }


    void specList() {
        OkGo.get(UrlContants.API_BASE_URL + UrlContants.SPEC_ALL_LIST).execute(new JsonCallback<BaseEntity<SpecEntity>>() {
            @Override
            public void onSuccess(BaseEntity<SpecEntity> baseEntity, Call call, Response response) {
                specEntity = baseEntity.getData();
                isChinaN = true;
                qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard());
                if (map.containsKey("standard"))
                    initQuality((String) map.get("standard"));
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

            @Override
            public void onCacheSuccess(BaseEntity<SpecEntity> baseEntity, Call call) {
                specEntity = baseEntity.getData();
                isChinaN = true;
                qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard());
                if (map.containsKey("standard"))
                    initQuality((String) map.get("standard"));
            }
        });
    }


    @OnClick({R.id.brand_btn, R.id.product_btn, R.id.stock_edit, R.id.main_quality_two, R.id.main_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_search:
                if (!TextUtils.isEmpty(descEdit.getText().toString())) {

                    map.put("describes", descEdit.getText().toString());
                } else {
                    map.remove("describes");
                }
                if (!TextUtils.isEmpty(totalEdit.getText().toString())) {

                    map.put("totalQuantity", totalEdit.getText().toString());
                } else {
                    map.remove("totalQuantity");
                }
                if (map.containsKey("productNameId")) {
                    if (map.containsKey("specId")) {
                        if (map.containsKey("standard")) {
                            if (map.containsKey("texture")) {
                                if (map.containsKey("stockStatus")) {
                                    mPresenter.disposeProduct(map);
                                } else {
                                    ToastUtils.showShort("库存不能为空");
                                }

                            } else {
                                ToastUtils.showShort("材质不能为空");
                            }
                        } else {
                            ToastUtils.showShort("标准不能为空");
                        }
                    } else {
                        ToastUtils.showShort("规格不能为空");
                    }
                } else {
                    ToastUtils.showShort("品名不能为空");
                }
                break;
            case R.id.brand_btn:
                BrandActivity.navToBrand(this, true);
                break;
            case R.id.stock_edit:
                AlertDialog dialog;
                ListAdapter itemlist = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stock);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("库存状况");
                builder.setAdapter(itemlist, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        stockEdit.setText(stock[item]);

                        if (map.containsKey("stockStatus")) {
                            if (map.get("stockStatus").equals(String.valueOf((item + 1) * 10))) {
                                map.remove("stockStatus");
                            } else {
                                map.put("stockStatus", String.valueOf((item + 1) * 10));
                            }
                        } else {
                            map.put("stockStatus", String.valueOf((item + 1) * 10));
                        }
                        //处理条目点击
                    }
                });
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.product_btn:
                ProductNameActivity.navToProductName(this);
                break;
            case R.id.area_btn:
                AreaActivity.navToArea(this);
                break;
            case R.id.main_quality_two:
                if (qualityTwo.isSelected()) {
                    qualityTwo.setSelected(false);
                } else {
                    qualityTwo.setSelected(true);
                }
                if (map.containsKey("standard")) {
                    initQuality((String) map.get("standard"));
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == Constants.Activity.AreaActivity) {
            AreaEntity areaEntity = (AreaEntity) data.getExtras().getSerializable(Constants.Params.AreaParams);
//            a.setText(areaEntity.getAreaName());
        } else if (requestCode == Constants.Activity.ProductNameActivity) {
            ProductNameEntity entity = (ProductNameEntity) data.getExtras().getSerializable(Constants.Params.ProductNameParams);
            if (map.containsKey("productNameId")) {
                if (map.get("productNameId").equals(entity.getId())) {
                    map.remove("productNameId");
                } else {
                    map.put("productNameId", entity.getId());
                }
            } else {
                map.put("productNameId", entity.getId());
            }
            productEdit.setText(entity.getProductName());
        } else if (requestCode == Constants.Activity.BrandActivity) {
            BrandEntity entity = (BrandEntity) data.getExtras().getSerializable(Constants.Params.BrandParams);
            if (TextUtils.isEmpty(entity.getBrandNameCn())) {
                brandEdit.setText(entity.getBrandNameEn());
            } else {
                brandEdit.setText(entity.getBrandNameCn());
            }
            if (map.containsKey("brandId")) {
                if (map.get("brandId").equals(entity.getId())) {
                    map.remove("brandId");
                } else {
                    map.put("brandId", entity.getId());
                }
            } else {
                map.put("brandId", entity.getId());
            }
        }
    }

    @Override
    public void onRequestStart() {
        mLoadDialog.show();
    }

    @Override
    public void onRequestError(String msg) {
        mLoadDialog.autoDismiss(true);
    }

    @Override
    public void onRequestEnd() {
        mLoadDialog.autoDismiss(true);
    }

    @Override
    public void onInternetError() {

    }

    @Override
    public void finishAdd() {
        ToastUtils.showShort("发布成功");
        finish();
    }
}
