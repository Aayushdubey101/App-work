package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var num1:TextView
    lateinit var num2:TextView
    lateinit var num3:TextView
    lateinit var num4:TextView
    lateinit var num5:TextView
    lateinit var num6:TextView
    lateinit var num7:TextView
    lateinit var num8:TextView
    lateinit var num9:TextView
    lateinit var num0:TextView
    lateinit var minus:TextView
    lateinit var multiply:TextView
    lateinit var divide:TextView
    lateinit var modulo:TextView
    lateinit var equal:TextView
    lateinit var result:TextView
    lateinit var clear:TextView
    lateinit var plus:TextView
    lateinit var dot:TextView
    lateinit var doubleZero:TextView
    lateinit var openBracket:TextView
    lateinit var closeBracket:TextView

    var str:String="";
    var exp= CharArray(100)
    var st=FloatArray(100)
    var top=-1
    var max=100

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        num1= findViewById(R.id.text1)
        num2 = findViewById(R.id.text2)
        num3= findViewById(R.id.text3)
        num4=findViewById(R.id.text4)
        num5= findViewById(R.id.text5)
        num6=findViewById(R.id.text6)
        num7= findViewById(R.id.text7)
        num8=findViewById(R.id.text8)
        num9= findViewById(R.id.text9)
        num0=findViewById(R.id.zero)
        dot=findViewById(R.id.dot)
        doubleZero= findViewById(R.id.doubleZero)
        multiply=findViewById(R.id.multiply)
        divide=findViewById(R.id.divide)
        modulo=findViewById(R.id.modulus)
        plus=findViewById(R.id.plus)
        equal=findViewById(R.id.equal)
        result=findViewById(R.id.result)
        clear=findViewById(R.id.clear)
        openBracket=findViewById(R.id.openBracket)
        closeBracket=findViewById(R.id.closeBracket)



        clear.setOnClickListener {
            result.text = ""
        }
        num1.setOnClickListener{
            resultText(str,"1")
        }
        num2.setOnClickListener{
            resultText(str,"2")
        }
        num3.setOnClickListener{
            resultText(str,"3")
        }
        num4.setOnClickListener{
            resultText(str,"4")
        }
        num5.setOnClickListener{
            resultText(str,"5")
        }
        num6.setOnClickListener{
            resultText(str,"6")
        }
        num7.setOnClickListener{
            resultText(str,"7")
        }
        num8.setOnClickListener{
            resultText(str,"8")
        }
        num9.setOnClickListener{
            resultText(str,"9")
        }
        num0.setOnClickListener{
            resultText(str,"0")
        }
        doubleZero.setOnClickListener{
            resultText(str,"00")
        }
        dot.setOnClickListener{
            resultText(str,".")
        }
        plus.setOnClickListener{
            resultText(str,"+")
        }
        minus.setOnClickListener{
            resultText(str,"-")
        }
        divide.setOnClickListener{
            resultText(str,"/")
        }
        multiply.setOnClickListener{
            resultText(str,"*")
        }
        modulo.setOnClickListener{
            resultText(str,"%")
        }
        openBracket.setOnClickListener{
            resultText(str,"(")
        }
        closeBracket.setOnClickListener{
            resultText(str,")")
        }


        equal.setOnClickListener {
            for(i in 0 until str.length){
                exp[i]=str[i]
            }
            val ans=evaluatePostfixExp(exp).toString()
            result.text = ans
        }

    }

    fun resultText( value:String, num :String){
        str = value+num
        result.text = str
    }


    fun evaluatePostfixExp(exp:CharArray):Float{
        var i=0
        var op1:Float
        var op2:Float
        var value:Float
        while (i < str.length){
            if(exp[i].isDigit()){
                push(st,(exp[i]-'0').toFloat())
            }else{
                op2=pop(st)
                op1=pop(st)
                value=when(exp[i]){
                    '+'-> op1+op2
                    '-'->op1-op2
                    '/'-> op1/op2
                    '*'->op1*op2
                    '%'->op1%op2
                    else->-1.0f
                }
                push(st,value)
            }
            i++
        }
        return (pop(st))
    }


    fun push(st:FloatArray,value:Float){
        if(top==max-1){
            println("Stack overflow")
        }else{
            top++
            st[top]=value
        }
    }


    fun pop(st:FloatArray):Float{
        var num:Float=-1f
        if(top==-1){
            println("Stack underflow")
        }else{
            num=st[top]
            top--
        }
        return  num
    }

}