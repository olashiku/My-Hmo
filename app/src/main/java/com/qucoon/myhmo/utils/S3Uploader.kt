package inc.qucoon.nativeveezah.utils

import android.content.Context
import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.Region
import java.io.File


object S3Uploader {

    fun upload(context: Context,credentialsProvider: AWSCredentials,bucketName:String,filepath:String,uploadfileName:String):TransferObserver {
        val s3 = AmazonS3Client(credentialsProvider,com.amazonaws.regions.Region.getRegion(Regions.US_EAST_2))
        val transferUtility = TransferUtility.builder().s3Client(s3).context(context).build()
        return transferUtility.upload(
            bucketName,   //this is the bucket name on S3
            uploadfileName,  //this is the path and name
            File(filepath),  //path to the file locally
            CannedAccessControlList.PublicRead //to make the file public
        )

    }
}