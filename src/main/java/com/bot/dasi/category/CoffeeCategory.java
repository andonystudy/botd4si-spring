package com.bot.dasi.category;

import com.bot.dasi.domain.Client;
import com.bot.dasi.domain.Coffee;
import com.bot.dasi.service.ClientService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class CoffeeCategory {

    private static final int COLOR_EMBED = 0xFA744F;

    String[] keywords = {
            "café", "cafe", "cafesito", "cafecito", "coffee"
    };

    String[][] respBeber = {
            {"Oh no!! el café esta muy caliente, **grita**  🥵 ...", "https://cdn191.picsart.com/232352489057202.jpg"},
            {"glup glup glup 🤩", "https://i.pinimg.com/236x/bd/ce/38/bdce387d189b69662531084f756f7956--love-photos-anime-love.jpg"},
            {"tranquilo amig@, nadie te lo va a quitar 😲", "https://d.wattpad.com/story_parts/275/images/15608e547a6856c9437957331555.jpg"}
    };

    ArrayList<Coffee> coffees = new ArrayList<>();
    Coffee coffee;

    @Autowired
    ClientService clientService;

    @PostConstruct
    public void init(){
        coffee = new Coffee();
        coffee.setId(1L);
        coffee.setName("Capuchino");
        coffee.setDescription("Si te gusta la leche, es la indicada.");
        coffee.setUriImage("https://i.pinimg.com/originals/ef/c1/3e/efc13e6e6b5d510fe0919252149a659b.gif");
        coffee.setPrice(10);
        coffees.add(coffee);

        coffee = new Coffee();
        coffee.setId(2L);
        coffee.setName("Americano");
        coffee.setDescription("Trump Lover?, la indicada.");
        coffee.setUriImage("https://i.pinimg.com/originals/a0/d4/ce/a0d4ce3a21e9f0e12c3f685469840025.gif");
        coffee.setPrice(20);
        coffees.add(coffee);

        coffee = new Coffee();
        coffee.setId(3L);
        coffee.setName("Expresso");
        coffee.setDescription("Perfecto para progamar en JAVA.");
        coffee.setUriImage("https://i.pinimg.com/originals/b9/76/3d/b9763d398d581a8a2916ea94682c8c4c.gif");
        coffee.setPrice(10);
        coffees.add(coffee);
    }

    public void coffeeService(MessageReceivedEvent event, long channelID){

        //if (event.getTextChannel().getIdLong() != channelID) return;

        String mess = event.getMessage().getContentRaw();
        EmbedBuilder eb = new EmbedBuilder();
        Long userId = event.getAuthor().getIdLong();

        if (mess.equals(">billetera") || mess.equals(">wallet")){
            Client findClient = clientService.findByUserDiscordId(userId);
            if (findClient == null){
                Client client = new Client();
                client.setUserDiscordId(userId);
                Client newClient = clientService.create(client);

                eb.setColor(0x16817A);
                eb.setAuthor("MI BILLETERA 💰 ... $" + newClient.getCoin());
            } else{
                eb.setColor(0x16817A);
                eb.setAuthor("MI BILLETERA 💰 ... $" + findClient.getCoin());
            }
            event.getTextChannel().sendMessage(eb.build()).queue();
        }

        if (mess.startsWith(">c")){
            Client client = clientService.findByUserDiscordId(userId);
            if (client == null){
                eb.setColor(0x16817A);
                eb.setAuthor("BILLETERA DE " + event.getAuthor().getName().toUpperCase() + " NO CREADA 💰");
                eb.setDescription("Por favor active su billetera consultando ... `>billetera`");
                event.getTextChannel().sendMessage(eb.build()).queue();
                return;
            }
            if (client.getCoin() < 10){
                eb.setColor(0x16817A);
                eb.setAuthor("BILLETERA DE " + event.getAuthor().getName().toUpperCase() + " 💰 ... " + client.getCoin());
                eb.setDescription("Cuenta con menos de $10, no puede realizar una compra 😥");
                event.getTextChannel().sendMessage(eb.build()).queue();
                return;
            }
        }

        for (String keyword: keywords){
            if (mess.contains(keyword) && mess.startsWith(">c")){
                eb.setColor(COLOR_EMBED);
                eb.setAuthor("COFFEE SHOP");
                eb.setTitle("Café?");
                eb.setDescription("**" + event.getAuthor().getName() + "** ¿Desea un café?, puede revisar nuestra lista ... `>c lista`");
                eb.setThumbnail("https://66.media.tumblr.com/53dac09a10e08e1fde7d1594c203e5e1/tumblr_pbpv7u4AnQ1qglptqo2_500.gif");
                event.getTextChannel().sendMessage(eb.build()).queue();
                return;
            }
        }

        for (String keyword: keywords){
            if (mess.contains(keyword) &&
                    (event.getTextChannel().getIdLong() == channelID ||
                    event.getTextChannel().getIdLong() == 706167638488776765L ||
                    event.getTextChannel().getIdLong() == 701942512964862033L ||
                    event.getTextChannel().getIdLong() == 712136033080442930L )){
                eb.setColor(COLOR_EMBED);
                eb.setAuthor("COFFEE SHOP");
                eb.setTitle("Café?");
                eb.setDescription("**" + event.getAuthor().getName() + "** ¿Desea un café?, puede revisar nuestra lista ... `>c lista`");
                eb.setThumbnail("https://66.media.tumblr.com/53dac09a10e08e1fde7d1594c203e5e1/tumblr_pbpv7u4AnQ1qglptqo2_500.gif");
                event.getTextChannel().sendMessage(eb.build()).queue();
                return;
            }
        }

        if (mess.equals(">c lista") || mess.equals(">c list")){
            eb.setColor(COLOR_EMBED);
            eb.setAuthor("COFFEE SHOP [LISTA]");
            eb.setDescription("Elija un café de nuestra lista ... `>c [número]`");
            coffees.forEach(coffee -> {
                eb.addField(coffee.getId().toString().concat(".-").concat(coffee.getName().concat(" 💶 ").concat(coffee.getPrice().toString())), coffee.getDescription(), false);
            });
            eb.setThumbnail("https://www.anime-planet.com/images/characters/waitress-himouto-umaru-chan-123819.jpg");
            event.getTextChannel().sendMessage(eb.build()).queue();
        }

        if (mess.startsWith(">c")){
            Client client = clientService.findByUserDiscordId(userId);
            for (Coffee coffee: coffees){
                if (mess.contains(coffee.getId().toString())){
                    if (client.getCoin() < coffee.getPrice()){
                        eb.setColor(0x16817A);
                        eb.setAuthor("BILLETERA DE " + event.getAuthor().getName().toUpperCase() + " 💰 ... " + client.getCoin());
                        eb.setDescription("No cuenta con el dinero suficiente para realizar esta compra. 😟");
                    }else{
                        clientService.subtractCoin(client, coffee.getPrice());
                        eb.setColor(COLOR_EMBED);
                        eb.setAuthor("COFFEE SHOP");
                        eb.setTitle(coffee.getName());
                        eb.setDescription("Disfrute de su café **"+ event.getAuthor().getName() +"** ... `>c beber`");
                        eb.setThumbnail(coffee.getUriImage());
                    }
                    event.getTextChannel().sendMessage(eb.build()).queue();
                    return;
                }
            }
        }

        if (mess.equals(">c beber") || mess.equals(">c drink")){
            eb.setColor(COLOR_EMBED);
            eb.setAuthor("COFFEE SHOP");
            eb.setTitle("Bebiendo ...");

            int number = (int)(Math.random() * respBeber.length);
            eb.setDescription(respBeber[number][0]);
            eb.setThumbnail(respBeber[number][1]);
            event.getTextChannel().sendMessage(eb.build()).queue();
        }

        if (mess.equals(">c thanks") || mess.equals(">c gracias")){
            eb.setColor(COLOR_EMBED);
            eb.setAuthor("COFFEE SHOP");
            eb.setTitle("THANK YOU !! ☺");
            eb.setDescription("Gracias por visitarnos **"+ event.getAuthor().getName() +"** , vuelv@ pronto.");
            eb.setThumbnail("https://www.wallpaperflare.com/static/410/241/219/girl-waitress-smile-willingness-wallpaper.jpg");
            event.getTextChannel().sendMessage(eb.build()).queue();
        }

    }
}
