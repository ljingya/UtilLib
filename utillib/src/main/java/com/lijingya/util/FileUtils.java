package com.lijingya.util;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijingya
 * @description 文件工具
 * @email lijingya@91118.com
 * @createDate 2018/11/27
 * @company 杭州天音
 */
public class FileUtils {

    private static final String TAG = "FileUtils";

    /***************增******************/
    /**
     * 根据路径创建文件夹
     *
     * @param path
     */
    public static boolean createDir(String path) {
        return createDir(getFileByPath(path));
    }

    /**
     * 创建文件夹
     *
     * @param file
     * @return
     */
    public static boolean createDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdir());
    }

    /**
     * 创建文件
     *
     * @param path
     * @return
     */
    public static boolean createFile(String path) {
        return createFile(getFileByPath(path));
    }

    /**
     * 创建文件
     *
     * @param file
     * @return
     */
    public static boolean createFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        if (!createDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建新文件之前删除旧文件
     *
     * @param filePath
     * @return
     */
    public static boolean createFileByDeleteOldFile(final String filePath) {
        return createFileByDeleteOldFile(getFileByPath(filePath));
    }

    /**
     * 创建新文件之前删除旧文件
     *
     * @param file
     * @return
     */
    public static boolean createFileByDeleteOldFile(final File file) {
        if (file == null) {
            return false;
        }
        //文件存在，但是删除未成功 ，返回false
        if (file.exists() && !file.delete()) {
            return false;
        }
        if (!createFile(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 复制文件夹到另一个文件夹
     *
     * @param srcDirPath
     * @param destDirPath
     * @return
     */
    public static boolean copyDir(String srcDirPath, String destDirPath) {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    /**
     * 复制文件夹
     *
     * @param srcFile
     * @param destFile
     * @return
     */
    private static boolean copyDir(File srcFile, File destFile) {
        return copyOrMoveDir(srcFile, destFile, false);
    }

    /**
     * 复制，移动
     *
     * @param srcFile
     * @param destFile
     * @param isMove
     * @return
     */
    private static boolean copyOrMoveDir(File srcFile, File destFile, boolean isMove) {
        return copyOrMoveDir(srcFile, destFile, new OnReplaceListener() {
            @Override
            public boolean onReplace() {
                return true;
            }
        }, isMove);
    }

    /**
     * @param srcDirPath
     * @param destDirPath
     * @param listener
     * @return
     */
    public static boolean copyDir(String srcDirPath, String destDirPath, OnReplaceListener listener) {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener);
    }

    /**
     * @param srcFile
     * @param destFile
     * @param listener
     * @return
     */
    private static boolean copyDir(File srcFile, File destFile, OnReplaceListener listener) {
        return copyOrMoveDir(srcFile, destFile, listener, false);
    }

    /**
     * @param srcDir
     * @param destDir
     * @param listener
     * @param isMove
     * @return
     */
    private static boolean copyOrMoveDir(File srcDir, File destDir, OnReplaceListener listener, boolean isMove) {
        if (srcDir == null || destDir == null) {
            return false;
        }
        String srcPath = srcDir.getPath() + File.separator;
        String destPath = destDir.getPath() + File.separator;
        if (srcPath.contains(destPath)) {
            return false;
        }

        if (!srcDir.exists() || !srcDir.isDirectory()) {
            return false;
        }
        if (destDir.exists()) {
            if (listener == null || listener.onReplace()) {
                if (!deleteAllinDir(destDir)) {
                    return false;
                }
            } else {
                return true;
            }
        }

        if (!createDir(destDir)) {
            return false;
        }

        File[] files = srcDir.listFiles();

        for (File file : files) {
            File newDestFile = new File(destPath + file.getName());
            if (file.isFile()) {
                if (!copyOrMoveFile(file, newDestFile, listener, isMove)) {
                    return false;
                }
            } else if (file.isDirectory()) {
                if (!copyOrMoveDir(file, newDestFile, listener, isMove)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 复制文件
     *
     * @param srcPath
     * @param destPath
     * @return
     */
    public static boolean copyFile(String srcPath, String destPath) {
        return copyFile(getFileByPath(srcPath), getFileByPath(destPath));
    }

    /**
     * @param srcFile
     * @param destFile
     * @return
     */
    private static boolean copyFile(File srcFile, File destFile) {
        return copyOrMoveFile(srcFile, destFile, false);
    }

    private static boolean copyOrMoveFile(File srcFile, File destFile, boolean isMove) {
        return copyOrMoveFile(srcFile, destFile, new OnReplaceListener() {
            @Override
            public boolean onReplace() {
                return true;
            }
        }, isMove);
    }

    /**
     * @param srcDirPath
     * @param destDirPath
     * @param listener
     * @return
     */
    public static boolean copyFile(String srcDirPath, String destDirPath, OnReplaceListener listener) {
        return copyFile(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener);
    }

    /**
     * @param srcFile
     * @param destFile
     * @param listener
     * @return
     */
    public static boolean copyFile(File srcFile, File destFile, OnReplaceListener listener) {
        return copyOrMoveFile(srcFile, destFile, listener, false);
    }

    /**
     * @param srcFile
     * @param destFile
     * @param listener
     * @param isMove
     * @return
     */
    public static boolean copyOrMoveFile(File srcFile, File destFile, OnReplaceListener listener, boolean isMove) {
        if (srcFile == null || destFile == null) {
            return false;
        }
        if (srcFile.equals(destFile)) {
            return false;
        }
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        if (destFile.exists()) {
            if (listener == null || listener.onReplace()) {
                if (!destFile.delete()) {
                    return false;
                }
            } else {
                return true;
            }
        }

        if (!createFile(destFile.getParentFile())) {
            return false;
        }
        try {
            return writeFileFromIs(destFile, new FileInputStream(srcFile)) && !(isMove && deleteFile(srcFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * @param srcPath
     * @param destPath
     * @return
     */
    public static boolean moveDir(String srcPath, String destPath) {
        return moveDir(getFileByPath(srcPath), getFileByPath(destPath));
    }

    /**
     * @param srcFile
     * @param destFile
     * @return
     */
    public static boolean moveDir(File srcFile, File destFile) {
        return copyOrMoveDir(srcFile, destFile, true);
    }

    /**
     * @param srcPath
     * @param destPath
     * @param listener
     * @return
     */
    public static boolean moveDir(String srcPath, String destPath, OnReplaceListener listener) {
        return moveDir(getFileByPath(srcPath), getFileByPath(destPath), listener);
    }

    /**
     * @param srcFile
     * @param destFile
     * @param listener
     * @return
     */
    public static boolean moveDir(File srcFile, File destFile, OnReplaceListener listener) {
        return copyOrMoveDir(srcFile, destFile, listener, true);
    }

    /**
     * 移动文件
     *
     * @param srcPath
     * @param destPath
     * @return
     */
    public static boolean moveFile(String srcPath, String destPath) {
        return moveFile(getFileByPath(srcPath), getFileByPath(destPath));
    }

    /**
     * @param srcFile
     * @param destFile
     * @return
     */
    public static boolean moveFile(File srcFile, File destFile) {
        return copyOrMoveFile(srcFile, destFile, true);
    }

    public static boolean moveFile(String srcPath, String destPath, OnReplaceListener listener) {
        return moveFile(getFileByPath(srcPath), getFileByPath(destPath), listener);
    }

    /**
     * @param srcFile
     * @param destFile
     * @param listener
     * @return
     */
    public static boolean moveFile(File srcFile, File destFile, OnReplaceListener listener) {
        return copyOrMoveFile(srcFile, destFile, listener, true);
    }

    /***************删******************/
    /**
     * 删除文件通过路径
     *
     * @param path
     * @return
     */
    public static boolean delete(String path) {
        return delete(getFileByPath(path));
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            deleteDir(file);
        }
        return deleteFile(file);
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    private static boolean deleteFile(File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());

    }

    /**
     * 删除文件夹
     *
     * @param dir
     */
    private static boolean deleteDir(File dir) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return false;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                if (!file.delete()) {
                    return false;
                }
            } else if (file.isDirectory()) {
                if (!deleteDir(file)) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 删除文件夹下的所有文件
     *
     * @param path
     * @return
     */
    public static boolean deleteAllInDir(String path) {
        return deleteAllinDir(getFileByPath(path));
    }


    /**
     * 删除文件夹下的所有文件
     *
     * @param dir
     * @return
     */
    public static boolean deleteAllinDir(File dir) {
        return deleteFilesInDirWidthFilter(dir, new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return true;
            }
        });
    }

    /**
     * 删除文件夹下符合过滤条件的文件
     *
     * @param dirPath
     * @param filter
     * @return
     */
    public static boolean deleteFilesInDirWidthFilter(String dirPath, FileFilter filter) {
        return deleteFilesInDirWidthFilter(getFileByPath(dirPath), filter);
    }


    /**
     * 删除文件夹下符合过滤条件的文件
     *
     * @param dir
     * @param filter
     * @return
     */
    public static boolean deleteFilesInDirWidthFilter(File dir, FileFilter filter) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return false;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (filter.accept(file)) {
                    if (file.isFile()) {
                        if (!file.delete()) {
                            return false;
                        }
                    } else if (file.isDirectory()) {
                        if (!deleteDir(file)) {
                            return false;
                        }
                    }

                }
            }
        }
        return true;
    }


    /**
     * 删除文件夹下的文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFilesInDir(String path) {

        return delteFilesInDir(getFileByPath(path));
    }

    /**
     * 删除文件夹下的文件
     *
     * @param dir
     * @return
     */
    private static boolean delteFilesInDir(final File dir) {
        return deleteFilesInDirWidthFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    /***************改******************/
    /**
     * 重命名通过路径
     *
     * @param filePath
     * @param newName
     * @return
     */
    public static boolean rename(String filePath, String newName) {
        return rename(getFileByPath(filePath), newName);
    }

    /**
     * 重命名文件
     *
     * @param file
     * @param newName
     * @return
     */
    public static boolean rename(File file, String newName) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        if (isSpaceStr(newName)) {
            return false;
        }
        if (newName.equals(file.getName())) {
            return true;
        }
        File newFile = new File(file.getParent() + File.separator + newName);
        return !newFile.exists() && file.renameTo(newFile);
    }
    /***************查******************/
    /**
     * 查询文件夹下的所有文件
     *
     * @param dirPath
     * @return
     */
    public static List<File> getListFilesInDir(String dirPath) {
        return getListFilesInDir(dirPath, false);
    }

    /**
     * @param dirPath
     * @param isRecursive true ：遍历子目录，
     * @return
     */
    public static List<File> getListFilesInDir(String dirPath, boolean isRecursive) {
        return getListFilesInDir(getFileByPath(dirPath), isRecursive);
    }

    /**
     * @param file
     * @param isRecursive
     * @return
     */
    public static List<File> getListFilesInDir(File file, boolean isRecursive) {
        return getListFilesInDirWidthFilter(file, new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return true;
            }
        }, isRecursive);
    }

    /**
     * @param dir
     * @param filter
     * @param isRecursive true ：遍历子目录，
     * @return
     */
    public static List<File> getListFilesInDirWidthFilter(File dir, FileFilter filter, boolean isRecursive) {
        if (!isDir(dir)) {
            return null;
        }
        List<File> fileList = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (filter.accept(dir)) {
                    fileList.add(file);
                }
                if (isRecursive && file.isDirectory()) {
                    fileList.addAll(getListFilesInDirWidthFilter(file, filter, isRecursive));
                }
            }
        }
        return fileList;
    }

    /**
     * @param dir
     * @param filter
     * @return
     */
    public static List<File> getListFilesInDirWidthFilter(String dir, FileFilter filter, boolean isRecursive) {
        return getListFilesInDirWidthFilter(getFileByPath(dir), filter, isRecursive);
    }

    /**
     * @param dir
     * @param filter
     * @return
     */
    public static List<File> getListFilesInDirWidthFilter(File dir, FileFilter filter) {
        return getListFilesInDirWidthFilter(dir, filter, false);
    }

    /**
     * @param dirPath
     * @param filter
     * @return
     */
    public static List<File> getListFilesInDirWidthFilter(String dirPath, FileFilter filter) {
        return getListFilesInDirWidthFilter(getFileByPath(dirPath), filter, false);
    }

    /**
     * 通过文件路径获取文件
     *
     * @param path
     * @return
     */
    public static File getFileByPath(final String path) {
        return isSpaceStr(path) ? null : new File(path);
    }

    /**
     * 判断文件
     *
     * @param file
     * @return
     */
    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    /**
     * 通过路径判断文件
     *
     * @param path
     * @return
     */
    public static boolean isFile(String path) {
        return isFile(getFileByPath(path));
    }

    /**
     * 判断目录
     *
     * @param file
     * @return
     */
    public static boolean isDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    /**
     * 判断是否是文件夹
     *
     * @param path
     * @return
     */
    public static boolean isDir(String path) {
        return isDir(getFileByPath(path));
    }


    interface OnReplaceListener {

        boolean onReplace();
    }


    /**
     * 从文件中读取数据，并写入另外一个文件
     *
     * @param file
     * @param is
     * @return
     */
    private static boolean writeFileFromIs(File file, FileInputStream is) {
        OutputStream os = null;

        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte[] b = new byte[2046];
            int len;
            while ((len = is.read(b, 0, 2046)) != -1) {
                os.write(b, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 判断路径的字符串是否是空白
     *
     * @param s
     * @return
     */
    private static boolean isSpaceStr(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
