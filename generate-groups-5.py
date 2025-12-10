from datetime import datetime, timedelta
from typing import List, Dict, Tuple, Optional
import csv


def generate_team_ids(num_teams: int, base_id: int = 100) -> List[int]:
    """
    Generate consecutive team IDs starting at `base_id`.
    Example for 5 teams, base_id=500: 500, 501, 502, 503, 504
    """
    return [base_id + i for i in range(num_teams)]


def round_robin_odd_teams(team_ids: List[int]) -> List[List[Tuple[int, int]]]:
    """
    Standard round-robin scheduler for an ODD number of teams.
    Uses a 'ghost' team to create byes, so each real team has one bye.

    Returns:
        List of rounds, each round is a list of (home, guest) tuples.
        For 5 teams -> 5 rounds, each team plays 4 games and has 1 bye.
    """
    if len(team_ids) % 2 == 0:
        raise ValueError("This function is intended for an ODD number of teams.")

    teams: List[Optional[int]] = list(team_ids) + [None]  # None = ghost
    n = len(teams)  # even
    max_rounds = n - 1  # equals original number of teams

    schedule: List[List[Tuple[int, int]]] = []

    for _ in range(max_rounds):
        round_games: List[Tuple[int, int]] = []

        for i in range(n // 2):
            t1 = teams[i]
            t2 = teams[n - 1 - i]

            # ignore pairings with ghost (bye)
            if t1 is not None and t2 is not None:
                round_games.append((t1, t2))

        schedule.append(round_games)

        # rotate all but the first element
        teams = [teams[0]] + [teams[-1]] + teams[1:-1]

    return schedule


def generate_gameplan(
    num_teams: int = 5,
    base_id: int = 500,
    total_rounds: int = 5,
    start_time_str: str = "08:00",
    game_duration_minutes: int = 10,
    category: int = 1,
):
    """
    Generate a gameplan for a single category with an ODD number of teams.
    - Uses round-robin with a ghost team (bye).
    - For 5 teams: 5 rounds, each team plays 4 games.

    Two halls (A & B) run in parallel:
    - Same timestamp for Hall A & Hall B within each timeslot
    - Time moves forward by `game_duration_minutes` per timeslot
    """
    if num_teams % 2 == 0:
        raise ValueError("This generator is meant for an ODD number of teams.")

    team_ids = generate_team_ids(num_teams, base_id)
    rounds = round_robin_odd_teams(team_ids)  # list[round][(home, guest)]

    # Safety: we only use up to total_rounds of the generated rounds
    rounds = rounds[:total_rounds]

    # Build list of games per round without time/hall
    rounds_games: List[List[Dict]] = [[] for _ in range(len(rounds))]
    game_id = 1

    for round_idx, pairings in enumerate(rounds):
        round_number = round_idx + 1
        for home, guest in pairings:
            rounds_games[round_idx].append(
                {
                    "round": round_number,
                    "game_id": game_id,
                    "home": home,
                    "guest": guest,
                    "category": category,
                }
            )
            game_id += 1

    # Assign halls & times
    start_time = datetime.strptime(start_time_str, "%H:%M")
    current_time = start_time
    gameplan: List[Dict] = []

    for round_games in rounds_games:
        i = 0
        while i < len(round_games):
            # Hall A
            g1 = round_games[i]
            g1["hall"] = "A"
            g1["time"] = current_time.strftime("%H:%M")
            gameplan.append(g1)

            # Hall B (if there is a second game in this timeslot)
            if i + 1 < len(round_games):
                g2 = round_games[i + 1]
                g2["hall"] = "B"
                g2["time"] = current_time.strftime("%H:%M")
                gameplan.append(g2)

            # Advance to next timeslot
            current_time += timedelta(minutes=game_duration_minutes)
            i += 2

    return gameplan


def print_gameplan_by_hall(gameplan: List[Dict]):
    """
    Print the gameplan grouped by hall, nicely formatted.
    """
    halls: Dict[str, List[Dict]] = {"A": [], "B": []}
    for g in gameplan:
        halls[g["hall"]].append(g)

    for hall in ("A", "B"):
        print("\n======================")
        print(f"        HALL {hall}")
        print("======================")
        print(f"{'Rnd':>3} {'GID':>4} {'Time':>5} {'Home':>5} {'Guest':>5} {'Cat':>3}")

        for g in sorted(halls[hall], key=lambda x: (x["round"], x["time"], x["game_id"])):
            print(
                f"{g['round']:3d} {g['game_id']:4d} {g['time']:>5} "
                f"{g['home']:5d} {g['guest']:5d} {g['category']:3d}"
            )


def export_gameplan_csv(gameplan: List[Dict], filename: str):
    """
    Export the gameplan to a CSV file.
    Columns: round, game_id, time, hall, home, guest, category
    """
    fieldnames = ["round", "game_id", "time", "hall", "home", "guest", "category"]

    with open(filename, "w", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(gameplan)

    print(f"CSV exported to: {filename}")


if __name__ == "__main__":
    # Single weird category: 5 teams, IDs 500–504
    gameplan = generate_gameplan(
        num_teams=5,
        base_id=8001,
        total_rounds=5,
        start_time_str="08:00",
        game_duration_minutes=10,
        category=5,  # e.g. category id 5
    )

    print_gameplan_by_hall(gameplan)
    export_gameplan_csv(gameplan, "weird_category_gameplan.csv")
