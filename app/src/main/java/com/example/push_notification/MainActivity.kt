package com.example.push_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    //고유 채널 아이디
    val CHANNEL_ID = "test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            displayNotification()
        }
    }

    //알림 만들어서 표시
    fun displayNotification() {
        createNotificationChannel()
        //고유 알림 아이디
        val notificationId = 1

        val textTitle = "test title"
        val textContent = "this is content."

        //알림의 탭 작업 설정
        val intent = Intent(this, AlertDetails::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }//AlertDetails 액티비티에 플래그 전달
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)//intent를 이용해 Notification에 필요한PendingIntent 생성

        //기본 알림 만들기
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)//아이콘
            .setContentTitle(textTitle)//제목
            .setContentText(textContent)//내용
            .setStyle(NotificationCompat.BigTextStyle()//확장 가능 알림 설정
                .bigText(textContent))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)//중요도
            .setContentIntent(pendingIntent)//유저가 탭할 때의 인텐트
            .setAutoCancel(true)//자동 알림 삭제
            .build()

        //알림 표시
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, notification)
        }
    }

    //채널 만들기, 중요도 설정
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /*알림의 구성
    작은 아이콘: setSmallIcon()
    앱 이름: 시스템에서 제공
    타임스탬프: 시스템에서 제공. setWhen()을 사용해 재정의하거나 setShowWhen(false)로 숨길 수 있음.
    큰 아이콘: (선택사항). setLargeIcon()
    제목: (선택사항). setContentTitle()
    텍스트: (산택사항). setContentText()
    */
    /*알림 작업
    7.0(API 24)부터 알림에서 직접 메시지 회신, 다른 텍스트에 입력 가능
    10(API 29)부터 플래폼에서 추천된 인텐트 기반 작업으로 작업 버튼 자동 생성 가능
     */
    /*
    여러번 중복되지 않게 알림 업데이트 시, 표시 방법을 고려해야 함.
    그룹으로 묶는 방법도 있음.
     */
    /*
    8.0(API 26)부터 모든 알림을 채널에 할당해야 함
     */
    /*
    알림의 중요도를 설정해 사용자 방해 수준이 달라짐.
     */
    /*
    포그라운드 서비스(미디어 플레이어 같은 것)는 알림이 필수. 이 알림은 다른 알림처럼 닫을 수 없음.
     */
    /*
    8.1(API 27)부터는 앱에서 초당 한 번까지 알림을을 울릴 수 있음.
    알림이 지나치게 많으면 시스템에서 일부 업데이트를 중단할 수 있음.
     */
    /*
    구형 기기에서 사용하려면 NotificationCompat과 하위 클래스, NotificationManagerCompat을 사용.
    NotificationCompat은 이후 플랫폼에 최신 메서드가 포함될 때 업데이트 됨. NoticationCompat에서 메서드 사용할 수 있다 하더라도
    해당 기능이 구형 기기에 제공된다는 보장은 없음.
    NotificationCompat.addAction()은 4.1(API 16) 이상을 실행하는 기기에서만 작업 버튼 표시.
     */
}