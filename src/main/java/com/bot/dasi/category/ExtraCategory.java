package com.bot.dasi.category;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;

@Component
public class ExtraCategory {

    public void ping(MessageReceivedEvent event, long channelID){
        String mess = event.getMessage().getContentRaw();
        Long thisChannelId = event.getTextChannel().getIdLong();

        if (mess.startsWith(">ping") && thisChannelId == channelID){
            event.getTextChannel().sendMessage("new pong").queue();
        }
    }

    public void codeCat(MessageReceivedEvent event, long channelID){
        String mess = event.getMessage().getContentRaw();
        Long thisChannelId = event.getTextChannel().getIdLong();

        if (mess.startsWith(">code")){
            String[] msgSplit = mess.split(" ");
            if (msgSplit.length == 1){
                event.getTextChannel().sendMessage("Asigne un código: `>code [code]` ... ex -> `>code 404`").queue();
            } else{
                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(0x3CFF89);
                eb.setAuthor("CODE ".concat(msgSplit[1]));
                eb.setThumbnail("https://http.cat/".concat(msgSplit[1]));
                event.getTextChannel().sendMessage(eb.build()).queue();
            }
        }
    }

    public void sendMsgWithBot(MessageReceivedEvent event, long channelID){
        String mess = event.getMessage().getContentRaw();
        Long thisChannelId = event.getTextChannel().getIdLong();

        if (mess.startsWith(">sm") && thisChannelId == channelID){
            String[] msgSplit = mess.split("-");
            List<TextChannel> channels = event.getMessage().getMentionedChannels();

            String sendChannel = (channels.size() == 0) ? "702274131416317962" : channels.get(0).getId();
            String sendMsg = (msgSplit.length == 1) ? "No se encontraron mensajes. 😟" : msgSplit[1];

            TextChannel textChannel = event.getGuild().getTextChannelById(sendChannel);
            textChannel.sendMessage(sendMsg).queue();
        }
    }

    public void messageUpdate(@Nonnull MessageUpdateEvent event, long channelID){
        String[] upMsg = { "Actualizando el mensaje eh ","Que te he pillado actualizando el mensaje ",
                "Te gusta actualizar eh, me haces recordar a windows " };

        event.getAuthor().getName();
        event.getTextChannel().sendMessage( upMsg[(int)(Math.random() * upMsg.length)] + event.getAuthor().getAsMention()).queue();
    }

    public void mentionBot(MessageReceivedEvent event){
        String[] resp = {
                "¿Porqué cuando contestamos una llamada decimos `¿aló?`?",
                "¿Donde esta la  otra mitad del medio oriente?",
                "Si ordinario es sinónimo de vulgar, ¿por qué extraordinario no significa muy vulgar?",
                "Una mujer embarazada nadando, ¿es un submarino?",
                "Si la música se reproduce ¿Porque no tiene hijos?.",
                "¿Los cangrejos piensan que los peces vuelan?",
                "¿Los vegetarianos comen galletas de animalitos?",
                "¿Los perros ladran en el mismo idioma?",
                "¿Dónde está la otra mitad del medio ambiente?",
                "Si un arquitecto muere, entonces, ¿pasa a otro plano?",
                "Si el agua es incolora, ¿Porqué la parte de una toalla que ha sido sumergida en agua es de color más oscuro que la parte seca?",
                "Cuando inventaron el reloj, ¿Cómo sabían qué hora era?",
                "¿Cuál es el sinónimo de la palabra sinónimo?",
                "¿Por qué la cómoda se llama así, si la cama es mucho más cómoda?",
                "¿Como es posible que no podamos prounciar la `R` mentalmente sin mover la lengua?",
                "¿Cuanto mas suicidas hay, menos suicidas hay?",
                "Cuando una película está doblada, ¿solo ves la mitad?",
                "Si cárcel y prisión son sinónimos, ¿por qué no lo son carcelero y prisionero?",
                "Si la piscina es honda, ¿el mar es Toyota?",
                "¿Por qué no podemos estornudar con los ojos abiertos?",
                "¿Por qué la palabra `abreviación` es tan larga?",
                "¿A dónde van todas las moscas en invierno?",
                "Si no debes hablar con extraños ¿Cómo hiciste amigos?",
                "¿En qué idioma piensa un sordo?",
                "Si un apocalipsis zombi pasa en las vegas ¿Se queda en las vegas?",
                "Si los zombies llegan a tu casa ..- `¿ZOMBIENVENIDOS?`",
                "Si un solo profesor no puede enseñar todos los cursos ¿Porque un solo alumno debe aprenderse todos?",
                "Si dormir es gratis ¿Porque cuesta tanto levantarnos?",
                "Si meto la calculadora al refrigerador, entonces ¿tengo todo fríamente calculado?",
                "¿Porque se llaman analgesicos? si se toman de forma oral.",
                "Si los que tienen pelo rojo son pelirrojos entonces ...¿Los que tienen el pelo grueso son peligrosos?"
        };

        String firstUserMentionedId = "";
        List<User> mentionedUsers = event.getMessage().getMentionedUsers();
        if(mentionedUsers.size() > 0) {
            firstUserMentionedId =  mentionedUsers.get(0).getId();
        }
        if(firstUserMentionedId.equals("714263141768495194")){
            event.getTextChannel().sendMessage(resp[(int)(Math.random() * resp.length)]).queue();
        }
    }
}
