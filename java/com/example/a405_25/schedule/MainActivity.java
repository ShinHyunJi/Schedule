package com.example.a405_25.schedule;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //field
    String temp;   //class 벗어나면 값이 제거됨. local과 다르게 인스턴스 변수는 초기화xx
                    //  String temp = "";  xxxxx   공유하는 공간이기 때문에 초기화되어서는 안됨. ㄴ

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 에어리어라고 볼 수 있음 field는 텅텅 비어있는데
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context ctx = MainActivity.this;
        final CalendarView cal = findViewById(R.id.calendar);
        final TimePicker time = findViewById(R.id.timePicker);
        final TextView today = findViewById(R.id.today);        //오늘 날짜 출력

        // JAVA에서 오늘 날짜 가져오는 방법
        //String sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date());
        //SimpleDateFormat error이유? type불일치                            //오늘 날짜
        //today.setText(sdf);
        today.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new Date()));


        final TextView year = findViewById(R.id.year);
        final TextView month = findViewById(R.id.month);
        final TextView date = findViewById(R.id.date);
        final TextView hour = findViewById(R.id.hour);
        final TextView minute = findViewById(R.id.minute);

        //기능과 속성에 따라 람다식으로 처리할 지 말지 결정@!


        // 화면에 뿌려주는 값을 안보여지게
        time.setVisibility(View.INVISIBLE);

        //final String temp = "";     //내가 설정한 날짜를 임시로 박기 위한 변수
        // final 계속 보관중.


        //날짜 변경해줌  왜 error>?? event값   null부분 삭제
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {   //달력 이벤트
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                temp = year+"-"+(month+1)+"-"+dayOfMonth;           //2018-11-16 형식으로 출력
                //변수를 어디에 위치시켜야 할 지 고민해볼것!
            }
        });


        //RadioButton id : rdDate        ==> SET DATE
        findViewById(R.id.rdDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.setVisibility(View.VISIBLE);
                time.setVisibility(View.INVISIBLE);
            }
        });

        //RadioButton id : rdTime         ==> SET TIME
        findViewById(R.id.rdTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.setVisibility(View.INVISIBLE);
                time.setVisibility(View.VISIBLE);
            }
        });

        // Button id : confirmBtn    예약완료 버튼
        findViewById(R.id.confirmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("선택한 날짜",temp);               //temp 초기화하면 안됨
                String[] arr = temp.split("-");

                year.setText(arr[0]);           //token 기준으로 2018-11-16 21:18
                month.setText(arr[1]);
                date.setText(arr[2]);
                hour.setText(String.valueOf(time.getHour()));       //방법1
                minute.setText(time.getMinute()+"");        //+"" 하는 이유?  방법2 : getMinute()의 int => String으로 변환시키기 위해서

            }
        });
    }
}
