# SNet

## Snet is a simple example of social network

### We use here next technologies:

- Spring Framework
- Spring Security
- Hibernate
- PostgreSQL as databas
- Bootstrap template
- REST
- JavaScript
                       
### You can find here :

- Registration (with JQuery validation)
- Custom Login form
- Profile page
- Chats and messages
- Friends,Invites, and search friends
- Workgroups and news

=======

### Messaging

Snet provides send and receive messages in real time. At this moment available dialog between two users, but further, planned enable conference between dozens of users.

#### Messages

For access to messages, on the page with list of available chats ( _/chats_ ), just click on button **Read messages**, after click user will load message's page. Also user can remove existed chat, by using buttom **Remove chat**.

![](http://i84.fastpic.ru/big/2016/1118/4b/73415a42090b0de54f8bd7be850e9e4b.png)

To send new message, user can use form at lower corner of page messages. Sended message wiil shown last one.

![](http://i84.fastpic.ru/big/2016/1118/69/47c4339db2735326077b29b6e5349069.png)

#### Chats

Chats contains user's messages, act as _logical_ container. To proceed to chat, user can use buttons in profile of another user, buttons near elemnt of list at page friends and button near each result of searching friends, at a corresponding page.

![](http://i83.fastpic.ru/big/2016/1116/cf/ef871c010624ec227f7a547dc22ee3cf.png)

If user starts new chat with another user, with which current user already have dialog twosome, current user will redirect at existed dialog. Otherwise will be created new chat.

>Moreover, in a situation where _current_ user have conference between vary of users, and exist _another_ user, which involved in to this conference. If _current_ user creates new chat with _another_, he will be redirected in dialog **twosome** with _another_ one. In case of that dialog not exist, it will be created. This feature provides by algorithm below:

``` java
...

    private List<Chat> searchingAlgorithm() {
        List<Chat> result = new ArrayList<>();
		Map<Chat, List<User>> discusses = new HashMap<>();
		
        // hayStack - source list of chats
        for (ChatRegistryUnit registryUnit : hayStack) {
            if (discusses.containsKey(registryUnit.getChat())) {
                discusses.get(registryUnit.getChat()).add(registryUnit.getUser());
            } else {
                discusses.put(registryUnit.getChat(), new ArrayList<>());
                discusses.get(registryUnit.getChat()).add(registryUnit.getUser());
            }

        }

        for (Map.Entry<Chat, List<User>> chatListEntry : discusses.entrySet()) {
            if (chatListEntry.getValue().size()==2) {
                result.add(chatListEntry.getKey());
            }
        }

        return result;
    }
...

```
As input in this method was given haystack is an List<ChatRegistryUnit>. ChatRegistryUnit - entity which contains users and chats. Given list contains all chats registry, where involve users: _current_ and _another_. Aim of given algorithm is to form list which contains chats, where discuss **only** our _current_ and _another_ users.

More closely you can see this algorithm, and it's testing in [DialogSearcherTest](https://github.com/khasang-incubator/SNet/blob/development/src/test/java/io/khasang/snet/entity/DialogSearcherTest.java)
