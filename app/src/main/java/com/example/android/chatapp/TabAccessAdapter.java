package com.example.android.chatapp;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessAdapter extends FragmentPagerAdapter {
    public TabAccessAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;
            case 1 :
                GroupsFragment groupFragment = new GroupsFragment();
                return groupFragment;
            case 2 :
                ContactsFragment contactsFragment = new ContactsFragment();
                return contactsFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0 :
                return "Chats";
            case 1 :
                return "Groups";
            case 2 :
                return "Contacts";
            default:
                return null;
        }

    }
}
