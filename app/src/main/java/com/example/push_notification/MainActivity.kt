package com.example.push_notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}