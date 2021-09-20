package com.sec.form.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

@Slf4j
public class UploadFileUtils {

    public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws IOException {
        UUID uuid = UUID.randomUUID();
        String savedName = uuid.toString() + "_" + originalName;
        //날짜별로 파일 업로드 경로 생성
        String savedPath = calcPath(uploadPath);
        File target = new File(uploadPath + savedPath, savedName);
        FileCopyUtils.copy(fileData, target);
        String uploadFileName = makeUploadFileName(uploadPath, savedName, savedPath);
        return uploadFileName;
    }

    private static String makeUploadFileName(String uploadPath, String savedName, String savedPath) {
        String uploadedFileName = uploadPath + savedPath + File.separator + savedName;
        return uploadedFileName.substring(uploadPath.length()).replace(File.separatorChar, '/'); // \->/ 로 replace 함
    }

    private static String calcPath(String uploadPath) {
        Calendar calendar = Calendar.getInstance();
        String yearPath = File.separator + calendar.get(Calendar.YEAR); //2021
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH)); //09
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE)); //30

        makeDir(uploadPath, yearPath, monthPath, datePath);

        log.info("datePath= {}", datePath);

        return datePath;
    }

    private static void makeDir(String uploadPath, String... paths) {
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }
        for (String path : paths) {
            File dirPath = new File(uploadPath + path);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

}
