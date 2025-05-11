
package domain;
import java.util.*;

public class Battle {
    private Trainer trainer1;
    private Trainer trainer2;
    private Trainer currentTrainer;
    private Trainer opponent;
    private Timer turnTimer;
    private static final int ACTION_TIME_LIMIT = 20000; // 20 segundos
    private boolean battleEnded;
    private Queue<BattleAction> actionQueue = new ArrayDeque<>();
    
    public Battle(Trainer trainer1, Trainer trainer2) {
        if (trainer1 == null || trainer2 == null) throw new IllegalArgumentException("Entrenadores inválidos");
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.currentTrainer = decideFirstTurn(trainer1, trainer2);
        this.opponent = (currentTrainer == trainer1) ? trainer2 : trainer1;
        validateTeams();
    }
	private Trainer decideFirstTurn(Trainer t1, Trainer t2) {
        return Math.random() < 0.5 ? t1 : t2;
    }

	public void startBattle() {
        while (!battleEnded) {
            startTurnTimer();
            currentTrainer.decideAction(this);
            processActions();
            checkBattleEnd();
            switchTurn();
        }
    }
    private void startTurnTimer() {
        turnTimer = new Timer();
        turnTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Si el tiempo se agota, se pasa el turno
                log(currentTrainer.getName() + " no realizó una acción a tiempo. Pasando turno.");
                switchTurn(); // Cambia al siguiente entrenador
            }
        }, ACTION_TIME_LIMIT);
    }


	private void switchTurn() {
        Trainer temp = currentTrainer;
        currentTrainer = opponent;
        opponent = temp;
    }

    public Trainer getOpponent(Trainer trainer) {
        return trainer == trainer1 ? trainer2 : trainer1;
    }

	public Trainer getCurrentTrainer(){
		return this.currentTrainer;
	}

	public List<Trainer> getTrainers(){
		return List.of(trainer1, trainer2);
	}


    public void processActions() {
        // Cancelar el temporizador si se pro
        
        cesan acciones
        if (turnTimer != null) {
            turnTimer.cancel();
        }
        
        while (!actionQueue.isEmpty()) {
            BattleAction action = actionQueue.poll();
            executeAction(action);
        }
    }

    private void executeAction(BattleAction action) {
        switch (action.getActionType()) {
            case MOVE:
                handleMoveAction(action);
                break;
            case SWITCH:
                handleSwitchAction(action);
                break;
            case ITEM:
                handleItemAction(action);
                break;
            case FLEE:
                endBattle(currentTrainer.getName() + " huyó!");
                break;
        }
        switchTurn();
    }

	private void handleSwitchAction(BattleAction action) {
        Pokemon newPokemon = action.getNewPokemon();
        Trainer trainer = currentTrainer;
        
        // Validaciones
        if (!trainer.getPokemonTeam().contains(newPokemon)) {
            throw new GameException("Pokémon no pertenece al equipo");
        }
        if (newPokemon.isFainted()) {
            throw new GameException("No se puede cambiar a un Pokémon debilitado");
        }
        
        // Cambiar Pokémon activo
        trainer.switchPokemon(newPokemon);
        log(trainer.getName() + " cambia a " + newPokemon.getName());
        
        
    }

    private void handleItemAction(BattleAction action) {
        Item item = action.getItem();
        Pokemon target = action.getTarget();
        Trainer trainer = currentTrainer;
        
        // Validaciones
        if (!trainer.getItems().contains(item)) {
            throw new GameException("El entrenador no posee este ítem");
        }
        if (item instanceof Revive && !target.isFainted()) {
            throw new GameException("Revive solo funciona en Pokémon debilitados");
        }
        
        // Aplicar efecto del ítem
        item.applyEffect(target);
        trainer.getItems().remove(item);
        log(trainer.getName() + " usa " + item.getName() + " en " + target.getName());
    }

      
    


    public void endBattle(String message) {
        battleEnded = true;
		if (turnTimer != null) {
            turnTimer.cancel();
		}
        log(message);
    }

    public boolean isBattleEnded(){
        return this.battleEnded;
    }

    private void log(String msg) {
        System.out.println(msg);
    }

	private void checkBattleEnd() {
        // Verificar si algún entrenador perdió todos sus Pokémon
        boolean trainer1Lost = !trainer1.hasAvailablePokemon();
        boolean trainer2Lost = !trainer2.hasAvailablePokemon();
        
        if (trainer1Lost || trainer2Lost) {
            String winnerMessage;
            
            if (trainer1Lost && trainer2Lost) {
                winnerMessage = "¡Empate! Ambos entrenadores perdieron sus Pokémon";
            } else if (trainer1Lost) {
                winnerMessage = trainer2.getName() + " gana la batalla!";
            } else {
                winnerMessage = trainer1.getName() + " gana la batalla!";
            }
            
            endBattle(winnerMessage);
        }
    }
	public void queueAction(BattleAction action) {
        actionQueue.add(action); // Añade la acción a la cola
    }

    public Battle getBattle() {
        return this;
    }
    private void validateTeams() {
        if (trainer1.getPokemonTeam().size() < 6 || 
            trainer2.getPokemonTeam().size() < 6) {
            throw new IllegalArgumentException("Cada entrenador debe tener 6 Pokémon");
        }
    }

    public String getResultMessage() {
        boolean t1Lost = !trainer1.hasAvailablePokemon();
        boolean t2Lost = !trainer2.hasAvailablePokemon();
        
        if (t1Lost && t2Lost) return "¡Empate!";
        if (t1Lost) return trainer2.getName() + " es el ganador!";
        return trainer1.getName() + " es el ganador!";
    }

    private void handleMoveAction(BattleAction action) {
        Movement move = action.getMove();
        Pokemon attacker = currentTrainer.getActivePokemon();
        Pokemon defender = opponent.getActivePokemon();
        
        if (!move.checkAccuracy()) {
            log(attacker.getName() + " falló " + move.getName());
            return;
        }
        
        int damage = attacker.calculateDamage(move, defender);
        defender.takeDamage(damage);
        move.reducePP(1);
        log(attacker.getName() + " usa " + move.getName() + " (" + damage + " daño)");
        
        if (defender.isFainted()) {
            log(defender.getName() + " se ha debilitado!");
            opponent.notifyPokemonFainted(defender);
        }
    }

}


