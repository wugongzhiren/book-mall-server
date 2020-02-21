package com.mall.server;

import net.dongliu.apk.parser.ApkFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        try (ApkFile apkFile = new ApkFile(new File("F:\\DragonAging.apk"))) {
            try {
                String manifestXml = apkFile.getManifestXml();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] xml = apkFile.getFileData("assets/default_cases.xml");
            String str2 = new String(xml);
            System.out.println(str2);
            //System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
