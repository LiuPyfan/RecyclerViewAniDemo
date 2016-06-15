package com.zdd.recyclerviewanidemo.ui.imain;

import java.util.ArrayList;

/**
 * @ClassName: RecyclerViewAniDemo
 * @CreateDate: 16/6/15 上午9:51
 * @Author: lucky
 * @Description:
 * @Version: [v1.0]
 */
public interface MainLoadData {
    void showProgressDialog();

    void hideProgressDialog();

    void showEerror();

    void undateList(ArrayList<String> updatedDatas);

}
