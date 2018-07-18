/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.conduit.plastic.global;

import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.user.UserUtils;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Jun on 2016/4/9.
 */
public class Constants {

    public static final int PER_PAGE = 15;


    public static final String PHPHUB_USER_PATH = "users";

    public static final String PHPHUB_TOPIC_PATH = "topics";

    public static final String DEEP_LINK_PREFIX = "phphub://";


    public static String SK = UserUtils.getInstance().getSk();
    public static String[] banners = new String[]{"http://dl.bizhi.sogou.com/images/2012/04/07/55365.jpg", "http://img.bizhi.sogou.com/images/2012/03/04/59381.jpg", "http://img.web07.cn/UpImg/Desk/201212/03/Desk80846031934241.jpg", "http://img4.imgtn.bdimg.com/it/u=518734417,3035173623&fm=23&gp=0.jpg"};

    private static int mainRequestCode = 100;
    private static int pageRequestCode = 200;

    public static UserInfo getCurrentUser() {
        List<UserInfo> info = DataSupport.where("isLogin = ?", "1").find(UserInfo.class);
        if (info.size() > 0) {
            Logger.i(info.get(0).getSk());

            return info.get(0);
        } else {
            return null;
        }
    }

    public static boolean isLogin() {
//        List<UserInfo> info = DataSupport.where("isLogin = ?", "1").find(UserInfo.class);
//        if (info.size() > 0) {
//            Logger.i(info.get(0).getSk());
//            SK = UserUtils.getInstance().getSk();
//            return true;
//        } else {
//            return false;
//        }
        if (UserUtils.getInstance().isLogin()) {
            SK = UserUtils.getInstance().getSk();
            return true;
        } else {
            return false;
        }
    }

    public interface Key {
        String TOPIC = "topic";
        String TOPIC_TYPE = "topic_type";
        String USER_DATA = "user_data";
        String IS_LOGIN = "is_login";
        String COMMENT_URL = "comment_url";
        String WEB_URL = "web_url";
        String WEB_TITLE = "web_title";
        String TOPIC_IMAGE_URL = "topic_image_url";
        String TOKEN = "token";
        String TOPIC_ID = "topic_id";
        String USER_ID = "user_id";
        String PREVIEW_TOPIC_TITLE = "preview_topic_title";
        String PREVIEW_TOPIC_CONTENT = "preview_topic_content";
        String THEME_MODE = "theme_mode";
    }

    public interface Token {
        String AUTH_TYPE_GUEST = "client_credentials";
        String AUTH_TYPE_USER = "login_token";
        String AUTH_TYPE_REFRESH = "refresh_token";
    }

    public interface Topic {
        String EXCELLENT = "excellent";//推荐
        String NEWEST = "newest";//最新
        String VOTE = "vote";//热门
        String NOBODY = "nobody";//零回复
        String WIKI = "wiki";//社区WIKI
        String JOBS = "jobs";//热门招聘
    }

    public interface User {
        int USER_TOPIC_FOLLOW = 1;
        int USER_TOPIC_VOTES = 2; //赞过话题
        int USER_TOPIC_MY = 3; //
    }

    public interface Params {
        String SearchParams = "product";
        String SearchType = "stype";
        String DealParams = "deal";
        String SellerParams2 = "seller2";
        String HomeParams = "home";
        String SellerParams = "seller";
        String AreaParams = "area";
        String ProductNameParams = "productName";
        String SpecParams = "specName";
        String StandardParams = "standardName";
        String TextureParams = "textureName";
        String BrandParams = "brand";
    }

    public interface TopicOpt {
        int TOPIC_VOTE_UP = 5; //赞同话题
        int TOPIC_VOTE_DOWN = 6;//反对话题
    }

    public interface Activity {
        int BrandActivity = 200;
        int AreaActivity = 210;
        int ProductNameActivity = 220;
        int SearchActivity = 230;
        int UserInfoEditActivity = 240;
        int SpecActivity = 250;
        int StandardActivity = 260;
        int TextureActivity = 270;
    }

    public interface Theme {
        String Blue = "blue";
        String White = "white";
        String Gray = "gray";
        String Black = "black";
    }

}
