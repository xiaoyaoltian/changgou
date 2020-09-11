package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author HM
 * @version 1.0
 * @date 2020/9/4 22:41
 * 实现FastDFS文件管理
 * 文件上传
 * 文件删除
 * 文件下载
 * 文件信息获取
 * Storage信息获取
 * Tracker信息获取
 */
public class FastDFSUtil {
    /**
     * 加载Tracker链接信息
     */
    static {
        try {
            //查找classpath的文件路径
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            //加载Tracker链接信息
            ClientGlobal.init(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     *
     * @param fastDFSFile :上传的文件信息封装
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", fastDFSFile.getAuthor());

        //获取TrackerServer
        TrackerServer trackerServer = getTrackerServer();

        //获取Storageclient
        StorageClient storageClient = getStorageClient(trackerServer);

        /**
         * 通过StorageClient访问Storage，实现文件上传,并且获取文件上传后的存储信息
         *  1:上传文件的字节数组
         *  2:文件的扩展名jpg
         *  3:附加参数―比如:拍摄地址:北京
         *
         *   uploads[]
         *      uploads[0]:文件上传所存储的Storage的组名字  group1
         *      uploads[1]:文件存储到Storage上的文件名字   M00/02/44/itheima.jpg
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;
    }

    /**
     * 获取文件信息
     *
     * @param groupName      :文件的组名group1
     * @param remoteFileName : 文件的存储路径名字 MO0/00/00/wKjThF1E8O-AG268AAnAAJuzIB4474.jpg
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception {
        //获取TrackerServer
        TrackerServer trackerServer = getTrackerServer();

        //获取Storageclient
        StorageClient storageClient = getStorageClient(trackerServer);

        //获取文件信息
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    /**
     * 获取文件下载
     *
     * @param groupName      :文件的组名group1
     * @param remoteFileName : 文件的存储路径名字 MO0/00/00/wKjThF1E8O-AG268AAnAAJuzIB4474.jpg
     * @return
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception {
        //获取TrackerServer
        TrackerServer trackerServer = getTrackerServer();

        //获取Storageclient
        StorageClient storageClient = getStorageClient(trackerServer);

        //文件下载
        byte[] buffer = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(buffer);
    }

    /**
     * 文件删除
     *
     * @param groupName      :文件的组名group1
     * @param remoteFileName : 文件的存储路径名字 MO0/00/00/wKjThF1E8O-AG268AAnAAJuzIB4474.jpg
     * @return
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception {
        //获取TrackerServer
        TrackerServer trackerServer = getTrackerServer();

        //获取Storageclient
        StorageClient storageClient = getStorageClient(trackerServer);

        //文件删除
        storageClient.delete_file(groupName, remoteFileName);

    }

    /**
     * 获取storages信息
     *
     * @return
     * @throws Exception
     */
    public static StorageServer getStorages() throws Exception {
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的链接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取Storage信息
        return trackerClient.getStoreStorage(trackerServer);
    }

    /**
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     *
     * @param groupName      :组名
     * @param remoteFileName : 文件存储完整名
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws Exception {
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取服务信息
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    /**
     * 获取Tracker信息
     *
     * @throws Exception
     */
    public static String getTrackerInfo() throws Exception {
        //获取TrackerServer
        TrackerServer trackerServer = getTrackerServer();

        //Tracker的IP，HTTP端口
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int tracker_http_port = ClientGlobal.getG_tracker_http_port(); //8080
        String url = "http://" + ip + ":" + tracker_http_port;
        return url;
    }

    /**
     * 获取Tracker
     *
     * @return
     * @throws IOException
     */
    public static TrackerServer getTrackerServer() throws IOException {
        //创建TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    /**
     * 获取Storage
     *
     * @param trackerServer
     * @return
     */
    public static StorageClient getStorageClient(TrackerServer trackerServer) {
        //通过TrackerServer的链接信息可以获取Storage的链接信息,创建StorageClient对象存储Storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    public static void main(String[] args) throws Exception {
        //文件上传
/*        FileInfo fileInfo = getFile("group1", "MO0/00/00/wKjThF1E8O-AG268AAnAAJuzIB4474.jpg");
        System.out.println(fileInfo.getSourceIpAddr());
        System.out.println(fileInfo.getFileSize());*/

        //文件下载
        /*InputStream is = downloadFile("group1", "MO0/00/00/wKjThF1E8O-AG268AAnAAJuzIB4474.jpg");
        //将文件写入到本地磁盘
        FileOutputStream os = new FileOutputStream("D:/1.jpg");
        byte[] buffer = new byte[1024];
        while (is.read(buffer) != -1) {
            os.write(buffer);
        }
        os.flush();
        os.close();
        is.close();*/

        //文件删除
        //deleteFile("group1", "MO0/00/00/wKjThF1E8O-AG268AAnAAJuzIB4474.jpg");

        //获取storages信息
        //StorageServer storageServer = getStorages( ;
        //System.out.println(storageServer.getStorePathIndex();
        //System.out.println(storageServer.getInetSocketAddress().getHostString());//IP信息

        //获取Storage组的IP和端口信息
       /* ServerInfo[] groups = getServerInfo("groupl",
                "M00/00/00/wKjThF1E95SAZkDVAnAAJuzIB4821.jpg");
        for (ServerInfo group : groups) {
            System.out.println(group.getIpAddr());
            System.out.println(group.getPort());
        }*/
        //获取Tracker信息
        System.out.println(getTrackerInfo());

    }
}
