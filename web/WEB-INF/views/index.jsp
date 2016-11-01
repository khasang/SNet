<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Sign-Up/Login Form</title>
  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>
  <div class="form">
      
      <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login1">Log In</a></li>
      </ul>
      
      <div class="tab-content">
        <div id="signup">   
          <h1>Sign Up for Free</h1>
          
          <form action="/" method="post">
          

            <div class="field-wrap">
              <label>
                Login<span class="req">*</span>
              </label>
              <input type="text" id="login" required autocomplete="off" />
            </div>

          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input type="email" id="email" required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <input type="password" id="password" required autocomplete="off"/>
          </div>
          
          <button type="submit" class="button button-block" onclick="RestPost()"/>Get Started</button>
          
          </form>

        </div>
        
        <div id="login1">
          <h1>Welcome Back!</h1>
          
          <form action="/" method="post">
          
            <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <input type="email"required autocomplete="off"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>
          
          <button class="button button-block"/>Log In</button>
          
          </form>

        </div>
        
      </div><!-- tab-content -->

</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

      <script src="/js/index.js"></script>
  <script type="text/javascript">
    var service = '/users/register';
    var RestPost = function () {
      var JSONObject = {
        'login': $('#login').val(),
        'mail': $('#email').val(),
        'password': $('#password').val(),

      };
      $.ajax({
        type: 'POST',
        url: service,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(JSONObject),
        dataType: 'json',
        async: false,
        success: function (result) {
          $('#response').html(JSON.stringify(result));
        },
        error: function (jqXHR, textStatus, errorThrown) {
          $('#response').html(JSON.stringify(jqXHR));
        }
      });
    };

  </script>
</body>
</html>
