




   // Initialize Firebase
   var config = {
    apiKey: "AIzaSyB5QZqwCyKMcF4dqqzKcEW9BshtEd4Vcco",
    authDomain: "tcs-blockathon-2018.firebaseapp.com",
    databaseURL: "https://tcs-blockathon-2018.firebaseio.com",
    projectId: "tcs-blockathon-2018",
    storageBucket: "tcs-blockathon-2018.appspot.com",
    messagingSenderId: "143835828628"
  };
  firebase.initializeApp(config);
  // Get a reference to the database service
  var ref = firebase.database().ref();

  document.getElementById("signinform").addEventListener("submit", event => {
    event.preventDefault();


    
  var uname=document.getElementById("loginname").value;
  var upass=document.getElementById("loginpass").value;
    console.log(uname+" "+upass);
    ref.child('users').child('doctors').on("value", function(snapshot) {
      console.log(snapshot.val());
      if(snapshot.child(uname).exists()){
          console.log("present");
          if(snapshot.child(uname).val() == upass){
              console.log('log in success`!');
              window.location = "QRverify.html?id="+uname;
          }
      }
      else{
        console.log("No Entry");
      }
   }, function (error) {
      console.log("Error: " + error.code);
   });

});


//Display Toggling Function Starts here
// function signUp() {
//   console.log("Reached SignUp");

//   document.getElementById("signup").style.display = "block"; //Only this is activated
//   document.getElementById("signin").style.display = "none";
//   document.getElementById("otpsend").style.display = "none";
//   document.getElementById("otpcheck").style.display = "none";
//   document.getElementById("changepass").style.display = "none";
// }



// (function ($) {
//     "use strict";

//     /*==================================================================
//     [ Validate ]*/
//     var input = $('.validate-input .input100');

//     $('.validate-form').on('submit',function(){
//         var check = true;

//         for(var i=0; i<input.length; i++) {
//             if(validate(input[i]) == false){
//                 showValidate(input[i]);
//                 check=false;
//             }
//         }

//         return check;
//     });


//     $('.validate-form .input100').each(function(){
//         $(this).focus(function(){
//            hideValidate(this);
//         });
//     });

//     function validate (input) {
//         if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
//             if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
//                 return false;
//             }
//         }
//         else {
//             if($(input).val().trim() == ''){
//                 return false;
//             }
//         }
//     }

//     function showValidate(input) {
//         var thisAlert = $(input).parent();

//         $(thisAlert).addClass('alert-validate');
//     }

//     function hideValidate(input) {
//         var thisAlert = $(input).parent();

//         $(thisAlert).removeClass('alert-validate');
//     }
    
//     /*==================================================================
//     [ Show pass ]*/
//     var showPass = 0;
//     $('.btn-show-pass').on('click', function(){
//         if(showPass == 0) {
//             $(this).next('input').attr('type','text');
//             $(this).find('i').removeClass('fa-eye');
//             $(this).find('i').addClass('fa-eye-slash');
//             showPass = 1;
//         }
//         else {
//             $(this).next('input').attr('type','password');
//             $(this).find('i').removeClass('fa-eye-slash');
//             $(this).find('i').addClass('fa-eye');
//             showPass = 0;
//         }
        
//     });

// })(jQuery);