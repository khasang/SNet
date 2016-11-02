<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Page</title>
</head>
<body>
<table width="100%" style="border-collapse:collapse;">
    <tr>
        <td width="150px" valign="top"><!--/Левая старана начало-->
            <style>
                div.mylpag{border:1px solid #d2d2d2;background:#ffffff;}
                div.mylpag:hover{border:1px solid #0085E5;}
                .mylivepage{padding:4px;border:1px solid #d2d2d2;background:#f2f2f2;}
                .mylivepage:hover{background:#ffffff;}
            </style>
            <table width="150px" style="border-collapse:collapse;">
                <tr>
                    <td class="avatare"><?if( $_AVATAR$)?><img src="<?substr($_AVATAR$,strpos($_AVATAR$,'src')+5,strpos(substr($_AVATAR$,strpos($_AVATAR$,'src')+5), '>')-3)?>" width="150px"><?else?><img src="http://deejaay.ru/watermark/myMeg.png" width="150px"><?endif?></td>
                </tr>
                <!--/Меню-->
                <tr>
                    <td class="mylivepage"><a href="$PM_URL$" style="text-decoration:none;">Сообщения <?if($UNREAD_PM$)?><font color="#ce2d2d">($UNREAD_PM$)</font><?endif?></a></td>
                </tr>
                <tr>
                    <td class="mylivepage"><a href="$_CHANGE_DETAILS_URL$" style="text-decoration:none;">Редактировать</a></td>
                </tr>
                <?if!($_IS_OWN_PROFILE$)?>
                <tr>
                    <td>
                        <div class="mylpag" style="margin-top:10px;"><a href="$PERSONAL_PAGE_LINK$" style="text-decoration:none;">
                            <table width="100%" style="border-collapse:collapse;">
                                <tr>
                                    <td rowspan="2" width="30px"><img src="$USER_AVATAR_URL$" width="30px" border="0"></td>
                                    <td valign="top"><div width="auto" style="border-bottom:1px dotted #d2d2d2;"><font color="#0085E5"><b>$USERNAME$</b></font></div></td>
                                </tr>
                                <tr>
                                    <td><font color="#d2d2d2" size="1pt">Ваша страница</font></td>
                                </tr>
                            </table></a></div>
                    </td>
                </tr> <?endif?>
                <!--/Меню конец-->
            </table>
            <img alt="" border="0" src="<?substr('$_AVATAR$',28,3)?>"/>
        </td><!--/Левая старана конец-->
        <td valign="top"><!--/Правая старана начало-->
            <table style="border-collapse:collapse;margin-top:3px;">
                <tr>
                    <td class="leftiks">Имя:</td>
                    <td class="rightiks">$_NAME$</td>
                </tr>
                <tr>
                    <td class="leftiks">Ник:</td>
                    <td class="rightiks">$_USERNAME$</td>
                </tr>
                <tr>
                    <td class="leftiks">Возраст:</td>
                    <td class="rightiks"><?if($_AGE$)?>$_AGE$<?else?><font color="#aab7c5">Не указано.</font><?endif?></td>
                </tr>
                <tr>
                    <td class="leftiks">Пол:</td>
                    <td class="rightiks"><?if($_GENDER_NAME$)?>$_GENDER_NAME$<?else?><font color="#aab7c5">Не указано.</font><?endif?></td>
                </tr>
                <tr>
                    <td class="leftiks">Место проживания:</td>
                    <td class="rightiks"><?if($_COUNTRY$)?>$_COUNTRY$<?else?><font color="#aab7c5">Не указано.</font><?endif?></td>
                </tr>
                <tr>
                    <td class="leftiks">Статус:</td>
                    <td class="rightiks"><?if($_IS_OWN_PROFILE$)?><font color="#aab7c5">Online</font><?else?>$_STATUS$<?endif?></td>
                </tr>
                <tr>
                    <td class="promij" colspan="2"></td>
                </tr>
                <tr>
                    <td class="leftiks">Тип аккаунта:</td>
                    <td class="rightiks">$_GROUP_NAME$</td>
                </tr>
                <tr>
                    <td class="leftiks">Должность:</td>
                    <td class="rightiks">$_RANK_NAME$</td>
                </tr>
                <tr>
                    <td class="leftiks">Титул:</td>
                    <td class="rightiks"><?if($_TITLE$)?>$_TITLE$<?else?><font color="#aab7c5">Не присвоен</font><?endif?></td>
                </tr>
                <tr>
                    <td class="promij" colspan="2"></td>
                </tr>
                <tr>
                    <td class="leftiks">Дата Рождения:</td>
                    <td class="rightiks"><?if($_BIRTHDAY$)?>$_BIRTHDAY$<?else?><font color="#aab7c5">Не указано.</font><?endif?></td>
                </tr>
                <tr>
                    <td class="leftiks">Зарегистрирован:</td>
                    <td class="rightiks">$_REG_TIME$</td>
                </tr>
                <tr>
                    <td class="leftiks">Последний визит:</td>
                    <td class="rightiks">$_LOG_TIME$</td>
                </tr>
                <tr>
                    <td class="promij" colspan="2"></td>
                </tr>
                <tr>
                    <td class="leftiks">Сайт:</td>
                    <td class="rightiks"><?if($_WWW$)?>$_WWW$<?else?><font color="#aab7c5">Не указано.</font><?endif?></td>
                </tr>
                <tr>
                    <td class="leftiks">ICQ:</td>
                    <td class="rightiks"><?if($_ICQ$)?>$_ICQ$<?else?><font color="#aab7c5">Не указано.</font><?endif?></td>
                </tr>
                <tr>
                    <td class="leftiks">E-mail:</td>
                    <td class="rightiks"><?if($_IS_OWN_PROFILE$)?>$_EMAIL$ <?if($_EMAIL_IS_HIDDEN$)?><span style="font-size:7pt;color:#d2d2d2;">(Адрес скрыт)</span><?endif?><?else?><?if($MODER_PANEL$)?><a href="mailto:$_EMAIL$">$_EMAIL$</a><?if(!$_EMAIL_IS_VERIFIED$)?> <span style="color:#ce2d2d;" title="Not verified e-mail">*</span><?endif?><?else?><?if($_SEND_EMAIL_URL$)?><a href="$_SEND_EMAIL_URL$" style="text-decoraton:none;">Написать письмо пользователю</a><?else?><font color="#d2d2d2"><i>Адрес скрыт</i></font><?endif?><?endif?><?endif?><?if($_EMAIL_VERIFICATION_URL$)?>[ <b><a style="text-decotation:none;" href="$_EMAIL_VERIFICATION_URL$"><span style="color:red">Подтвердить e-mail</span></a></b> ]<?endif?></td>
                </tr>
                <?if($_SIGNATURE$)?><tr>
                <td class="promij" colspan="2" style="padding:2px;color:#808080;text-shadow:0px 1px #ffffff;"><center>Подпись</center></td>
            </tr>
                <tr>
                    <td class="podpee" colspan="2"><font color="#404040">$_SIGNATURE$</font></td>
                </tr><?endif?>
            </table>

            <?if($MODER_PANEL$)?><table style="border-collapse:collapse;margin-top:10px;">
                <tr>
                    <td class="leftiks">Редактирование:</td>
                    <td class="rightiks">$MODER_PANEL$</td>
                </tr>
            </table><?endif?>
        </td><!--/Правая старана конец-->
    </tr>
</table>

<style type="text/css">
    .activite_wall{display:block;background:#f9f9f9;padding:4px;width:560px;}
    .activite_wall:hover{background:#F9FCFF;}
    .avatare{
        padding:3px;
        border:1px solid #d2d2d2;
    }
    .leftiks{
        color:#666666;
        width:130px;
        padding:5px;
        background:#f5f5f5;
        border-top:1px solid #d2d2d2;
        border-bottom:1px solid #d2d2d2;
        border-left:1px solid #d2d2d2;
        border-right:1px dotted #d2d2d2;
    }
    .rightiks{
        width:420px;
        padding:5px;
        background:#f9f9f9;
        border-top:1px solid #d2d2d2;
        border-bottom:1px solid #d2d2d2;
        border-left:1px dotted #d2d2d2;
        border-right:1px solid #d2d2d2;
    }
    .promij{
        height:20px;
        background:url('http://deejaay.ru/shb_0_1/PPL/back_pers.png')no-repeat;
        border-left:1px solid #d2d2d2;
        border-right:1px solid #d2d2d2;
    }
    .podpee{
        background:url('http://deejaay.ru/shb_0_1/PPL/back_podp.png')no-repeat;
        padding:3px;
        border:1px solid #d2d2d2;
    }
</style>
</body>
</html>
