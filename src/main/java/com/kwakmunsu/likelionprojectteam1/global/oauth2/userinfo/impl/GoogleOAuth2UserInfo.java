package com.kwakmunsu.likelionprojectteam1.global.oauth2.userinfo.impl;

import static com.kwakmunsu.likelionprojectteam1.domain.member.entity.SocialType.GOOGLE;

import com.kwakmunsu.likelionprojectteam1.global.oauth2.userinfo.OAuth2UserInfo;
import java.util.Map;

public class GoogleOAuth2UserInfo implements OAuth2UserInfo {

    private final Map<String, Object> googleAttributes;

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        this.googleAttributes = attributes;
    }

    @Override
    public String getProvider() {
        return GOOGLE.name();
    }

    @Override
    public String getProfileUrl() {
        return googleAttributes.get("picture").toString();
    }

    @Override
    public String getSocialId() {
        return googleAttributes.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return googleAttributes.get("email").toString();
    }

    @Override
    public String getName() {
        return googleAttributes.get("name").toString();
    }

}